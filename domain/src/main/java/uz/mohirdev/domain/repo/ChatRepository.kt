package uz.mohirdev.domain.repo

import io.reactivex.rxjava3.core.Single
import uz.mohirdev.domain.model.Chat

interface ChatRepository {
    fun getChats() : Single<List<Chat>>
}