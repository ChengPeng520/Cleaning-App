// ChatMessage.kt
package com.example.cleaningapp.customer.chatroom

data class ChatroomUiState(val chatroomItems: List<ChatroomItemUiState> = listOf())

data class ChatroomItemUiState(
    val chatCustBackId: Int = 0,
    val customerId: Int = 0,
    val backstageId: Int = 0,
    val text: String = ""
)

