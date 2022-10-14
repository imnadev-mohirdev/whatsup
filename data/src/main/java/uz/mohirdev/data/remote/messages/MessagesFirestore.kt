package uz.mohirdev.data.remote.messages

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface MessagesFirestore {
    fun sendMessage(fromUserId: String, toUserId: String, message: String) : Completable
    fun getMessages(firstUserId: String, secondUserId: String) : Observable<List<MessageDocument>>
}