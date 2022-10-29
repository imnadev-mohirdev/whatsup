package uz.mohirdev.data.repo

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.mohirdev.data.mapper.toMessage
import uz.mohirdev.data.mapper.toUser
import uz.mohirdev.data.remote.auth.AuthFirebase
import uz.mohirdev.data.remote.files.ImageStorage
import uz.mohirdev.data.remote.messages.MessagesFirestore
import uz.mohirdev.data.remote.users.UsersFirestore
import uz.mohirdev.domain.model.Chat
import uz.mohirdev.domain.model.Message
import uz.mohirdev.domain.repo.ChatRepository
import java.io.InputStream

class ChatRepositoryImpl(
    private val usersFirestore: UsersFirestore,
    private val messagesFirestore: MessagesFirestore,
    private val authFirebase: AuthFirebase,
    private val imageStorage: ImageStorage
) : ChatRepository {

    override fun getChats(): Single<List<Chat>> = usersFirestore.getUsers().map { users ->
        users.mapNotNull { user ->
            user.toUser()?.let { Chat(user = it) }
        }
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun sendMessage(to: String, message: String): Completable =
        messagesFirestore.sendMessage(authFirebase.userId!!, to, message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun sendMessage(to: String, image: InputStream): Completable =
        imageStorage.upload(image).flatMapCompletable {
            messagesFirestore.sendMessage(authFirebase.userId!!, to, it)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getMessages(with: String): Observable<List<Message>> =
        messagesFirestore.getMessages(authFirebase.userId!!, with).map { messages ->
            messages.mapNotNull { it.toMessage(authFirebase.userId!!) }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}