package uz.mohirdev.presentation.screens.chat

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.mohirdev.domain.model.Chat
import uz.mohirdev.domain.model.Message
import uz.mohirdev.presentation.base.BaseFragment
import uz.mohirdev.presentation.databinding.FragmentChatBinding
import uz.mohirdev.presentation.screens.chat.ChatViewModel.Input

class ChatFragment(
    private val chat: Chat
) : BaseFragment<FragmentChatBinding>(FragmentChatBinding::inflate) {

    private val viewModel: ChatViewModel by viewModel()
    private val adapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.processInput(Input.SetChat(chat))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        viewModel.state.observe(::renderMessages) { it.messages }
    }

    private fun renderMessages(messages: List<Message>) {
        adapter.submitList(messages)
    }

    private fun initUI() = with(binding) {
        send.setOnClickListener {
            viewModel.processInput(Input.SendMessage(message.text.toString()))
            message.text = null
        }
        messages.adapter = adapter
    }
}