package uz.mohirdev.domain.usecase.chat

import uz.mohirdev.domain.repo.ChatRepository
import java.io.InputStream

class SendImageUseCase(
    private val chatRepository: ChatRepository
) {

    operator fun invoke(to: String, image: InputStream) = chatRepository.sendMessage(to, image)
}