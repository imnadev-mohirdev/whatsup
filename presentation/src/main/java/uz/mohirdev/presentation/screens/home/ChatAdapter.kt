package uz.mohirdev.presentation.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.mohirdev.domain.model.Chat
import uz.mohirdev.presentation.R
import uz.mohirdev.presentation.databinding.ItemChatBinding

class ChatAdapter(private val chats: List<Chat>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: Chat)  = with(binding) {
            Glide.with(root).load(R.drawable.ic_person).into(avatar)
            name.text = chat.user.name
            last.text = chat.user.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount() = chats.size
}