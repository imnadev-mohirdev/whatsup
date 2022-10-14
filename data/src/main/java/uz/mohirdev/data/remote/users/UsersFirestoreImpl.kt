package uz.mohirdev.data.remote.users

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import uz.mohirdev.data.remote.users.model.UserDocument

class UsersFirestoreImpl : UsersFirestore {

    private val users = Firebase.firestore.collection("users")

    override fun saveUser(user: FirebaseUser) : Completable = Completable.create { emitter ->

        val data = UserDocument(
            id = user.uid,
            phone = user.phoneNumber,
            name = "AnonymousUser" + user.phoneNumber?.takeLast(4),
            avatar = null
        )

        users.document(user.uid).set(data).addOnFailureListener {
            emitter.onError(it)
        }.addOnSuccessListener {
            emitter.onComplete()
        }
    }

    override fun getUsers(): Single<List<UserDocument>> = Single.create { emitter ->
        users.get().addOnFailureListener {
            emitter.onError(it)
        }.addOnSuccessListener { snapshot ->
            val userDocuments = snapshot.documents.mapNotNull {
                it.toObject(UserDocument::class.java)
            }
            emitter.onSuccess(userDocuments)
        }
    }
}