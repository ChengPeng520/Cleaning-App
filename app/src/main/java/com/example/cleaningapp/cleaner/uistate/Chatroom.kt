package com.example.cleaningapp.cleaner.uistate

data class ChatroomUiState(val chatroomItems: List<ChatroomItemUiState>)

data class ChatroomItemUiState(val id: Int, val fromId: Int = 0, val toId: Int = 0, val text: String = "")