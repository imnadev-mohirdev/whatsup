package uz.mohirdev.domain.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import uz.mohirdev.domain.model.Chat
import uz.mohirdev.domain.model.Message

interface ChatRepository {
    fun getChats() : Single<List<Chat>>
    fun sendMessage(to: String, message: String) : Completable
    fun getMessages(with: String) : Observable<List<Message>>
}