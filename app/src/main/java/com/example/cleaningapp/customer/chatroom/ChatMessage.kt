// ChatMessage.kt
package com.example.cleaningapp.customer.chatroom

data class ChatroomUiState(val chatroomItems: List<ChatroomItemUiState> = listOf())

data class ChatroomItemUiState(
    val id: Int = 0,
    val fromId: Int = 0,
    val toId: Int = 0,
    val text: String = ""
)
