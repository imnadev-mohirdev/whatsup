package uz.mohirdev.data.remote.messages

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.util.*

class MessagesFirestoreImpl : MessagesFirestore {

    private val messages = Firebase.firestore.collection("messages")

    override fun sendMessage(
        fromUserId: String,
        toUserId: String,
        message: String
    ): Completable = Completable.create { emitter ->
        val messageDocument = MessageDocument(
            id = UUID.randomUUID().toString(),
            message = message,
            time = Date(),
            members = listOf(fromUserId, toUserId).sorted().joinToString(),
            from = fromUserId
        )
        messages.document(messageDocument.id!!).set(messageDocument).addOnFailureListener {
            emitter.onError(it)
        }.addOnSuccessListener {
            emitter.onComplete()
        }
    }

    override fun getMessages(
        firstUserId: String,
        secondUserId: String
    ): Observable<List<MessageDocument>> = Observable.create { emitter ->
        messages
            .whereEqualTo("members", listOf(firstUserId, secondUserId).sorted().joinToString())
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    emitter.onError(e)
                    return@addSnapshotListener
                }

                if (snapshot == null) {
                    emitter.onNext(emptyList())
                    return@addSnapshotListener
                }

                val messageDocuments = snapshot.documents.mapNotNull {
                    it.toObject(MessageDocument::class.java)
                }.sortedBy { it.time }

                emitter.onNext(messageDocuments)
            }

    }
}