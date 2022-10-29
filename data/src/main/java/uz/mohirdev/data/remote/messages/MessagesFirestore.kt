package uz.mohirdev.data.remote.messages

import android.net.Uri
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface MessagesFirestore {
    fun sendMessage(fromUserId: String, toUserId: String, message: String) : Completable
    fun sendMessage(fromUserId: String, toUserId: String, image: Uri) : Completable
    fun getMessages(firstUserId: String, secondUserId: String) : Observable<List<MessageDocument>>
}