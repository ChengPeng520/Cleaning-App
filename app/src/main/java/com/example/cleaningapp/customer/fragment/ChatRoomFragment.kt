// ChatRoomFragment.kt
package com.example.cleaningapp.customer.fragment

import com.example.cleaningapp.customer.chatroom.ChatAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.customer.chatroom.ChatMessage
import com.example.cleaningapp.databinding.FragmentVictorChatRoomBinding

class ChatRoomFragment : Fragment() {
    private lateinit var binding: FragmentVictorChatRoomBinding
    private val chatAdapter = ChatAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentVictorChatRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.sendButton.setOnClickListener { sendMessage() }

        // 初始化假資料
        val dummyData = createDummyData()
        chatAdapter.setMessages(dummyData)
    }

    private fun sendMessage() {
        val message = binding.messageEditText.text.toString()
        if (message.isNotBlank()) {
            val chatMessage = ChatMessage(message, "User", System.currentTimeMillis())
            chatAdapter.addMessage(chatMessage)
            binding.messageEditText.text.clear()
        }
    }

    private fun createDummyData(): List<ChatMessage> {
        val dummyList = mutableListOf<ChatMessage>()
        dummyList.add(ChatMessage(
            "您好，請問有什麼問題可以協助幫忙呢?",
            "Bot", System.currentTimeMillis())
        )
        dummyList.add(ChatMessage(
            "我立刻要投訴那個王八\n" +
                    "我立刻要投訴那個王八\n" +
                    "我立刻要投訴那個王八\n" +
                    "我立刻要投訴那個王八\n" +
                    "我立刻要投訴那個王八\n" +
                    "我立刻要投訴那個王八",
            "User", System.currentTimeMillis())
        )
        // 加入更多假資料...
        return dummyList
    }
}
