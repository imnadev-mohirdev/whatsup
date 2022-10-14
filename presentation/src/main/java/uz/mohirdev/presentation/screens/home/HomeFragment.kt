package uz.mohirdev.presentation.screens.home

import android.os.Bundle
import android.view.View
import uz.mohirdev.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.mohirdev.domain.model.Chat
import uz.mohirdev.presentation.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(::renderChats) { it.chats }
        viewModel.state.observe(::renderLoading) { it.loading }
        viewModel.state.observe(::renderError) { it.error }
    }

    private fun renderError(error: Boolean) {

    }

    private fun renderLoading(loading: Boolean) {

    }

    private fun renderChats(chats: List<Chat>) = with(binding) {
        binding.chats.adapter = ChatAdapter(chats)
    }
}