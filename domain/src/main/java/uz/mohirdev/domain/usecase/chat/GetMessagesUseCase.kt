package uz.mohirdev.domain.usecase.chat

import uz.mohirdev.domain.repo.ChatRepository

class GetMessagesUseCase(
    private val chatRepository: ChatRepository
) {

    operator fun invoke(with: String) = chatRepository.getMessages(with)
}