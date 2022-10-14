package uz.mohirdev.domain.usecase.chat

import uz.mohirdev.domain.repo.ChatRepository

class SendMessageUseCase(
    private val chatRepository: ChatRepository
) {

    operator fun invoke(to: String, message: String) = chatRepository.sendMessage(to, message)
}