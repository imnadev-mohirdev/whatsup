package uz.mohirdev.domain.usecase.chat

import uz.mohirdev.domain.repo.ChatRepository

class GetChatsUseCase(
    private val chatRepository: ChatRepository
) {

    operator fun invoke() = chatRepository.getChats()
}