package com.example.cleaningapp.cleaner.uistate

import com.google.android.gms.tasks.Task

data class ChatroomUiState(val chatroomItems: List<ChatroomItemUiState> = mutableListOf())

data class ChatroomItemUiState(
    val msgClnBackId: Int = 0,
    val cleanerId: Int = 0,
    val backstageId: Int = 0,
    val text: String = "",
)