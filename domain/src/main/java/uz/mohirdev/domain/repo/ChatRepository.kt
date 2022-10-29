package uz.mohirdev.domain.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import uz.mohirdev.domain.model.Chat
import uz.mohirdev.domain.model.Message
import java.io.InputStream

interface ChatRepository {
    fun getChats() : Single<List<Chat>>
    fun sendMessage(to: String, message: String) : Completable
    fun sendMessage(to: String, image: InputStream) : Completable
    fun getMessages(with: String) : Observable<List<Message>>
}