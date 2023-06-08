package com.example.cleaningapp.backstage.usermanage.model

data class Chat(
    val chatItems: List<ChatItem> = listOf()
)

data class ChatItem(
    val id: Int = 0,
    val fromId: Int? = null,
    val toId: Int? = null,
    val text: String = ""
)