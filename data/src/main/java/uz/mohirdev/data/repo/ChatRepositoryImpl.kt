package uz.mohirdev.data.repo

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.mohirdev.data.mapper.toUser
import uz.mohirdev.data.remote.users.UsersFirestore
import uz.mohirdev.domain.model.Chat
import uz.mohirdev.domain.repo.ChatRepository

class ChatRepositoryImpl(
    private val usersFirestore: UsersFirestore
) : ChatRepository {

    override fun getChats(): Single<List<Chat>> = usersFirestore.getUsers().map { users ->
        users.mapNotNull { user ->
            user.toUser()?.let { Chat(user = it) }
        }
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}