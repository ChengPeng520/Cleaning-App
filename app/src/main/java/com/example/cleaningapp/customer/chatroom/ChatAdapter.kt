// ChatAdapter.kt
package com.example.cleaningapp.customer.chatroom

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.databinding.ItemVictorChatMessageBinding

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    private val chatMessages = mutableListOf<ChatMessage>()

    inner class ChatViewHolder(private val binding: ItemVictorChatMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatMessage: ChatMessage) {
            binding.messageTextView.text = chatMessage.content
            // 根據發送者決定訊息佈局的對齊方式，例如左對齊或右對齊
            val gravity = if (chatMessage.sender == "User") Gravity.END else Gravity.START
            binding.messageTextView.gravity = gravity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVictorChatMessageBinding.inflate(inflater, parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatMessage = chatMessages[position]
        holder.bind(chatMessage)
    }

    override fun getItemCount(): Int = chatMessages.size

    fun addMessage(chatMessage: ChatMessage) {
        chatMessages.add(chatMessage)
        notifyItemInserted(chatMessages.size - 1)
    }

    fun setMessages(messages: List<ChatMessage>) {
        chatMessages.clear()
        chatMessages.addAll(messages)
        Log.d("SDASD", chatMessages.size.toString())
        notifyDataSetChanged()
    }
}
