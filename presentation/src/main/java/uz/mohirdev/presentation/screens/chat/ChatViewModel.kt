package uz.mohirdev.presentation.screens.chat

import uz.mohirdev.domain.model.Chat
import uz.mohirdev.domain.model.Message
import uz.mohirdev.domain.usecase.chat.GetMessagesUseCase
import uz.mohirdev.domain.usecase.chat.SendMessageUseCase
import uz.mohirdev.presentation.base.BaseViewModel
import uz.mohirdev.presentation.screens.chat.ChatViewModel.*

class ChatViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase
) : BaseViewModel<State, Input, Effect>() {

    data class State(
        val messages: List<Message> = emptyList(),
        val loading: Boolean = false,
        val chat: Chat? = null
    )

    sealed class Input {
        object GetMessages : Input()
        data class SendMessage(val message: String) : Input()
        data class SetChat(val chat: Chat) : Input()
    }

    sealed class Effect {
        object ErrorSending : Effect()
        object ErrorGetting : Effect()
    }

    override fun getDefaultState() = State()

    override fun processInput(input: Input) {
        when (input) {
            Input.GetMessages -> getMessages()
            is Input.SendMessage -> sendMessage(input.message)
            is Input.SetChat -> setChat(input.chat)
        }
    }

    private fun setChat(chat: Chat) {
        updateState { it.copy(chat = chat) }
        getMessages()
    }

    private fun sendMessage(message: String) = sendMessageUseCase(current.chat!!.user.id, message)
        .doOnError {
            emitEffect(Effect.ErrorSending)
        }.subscribe()

    private fun getMessages() = getMessagesUseCase(current.chat!!.user.id)
        .doOnNext { messages ->
            updateState { it.copy(messages = messages) }
        }.subscribe()
}