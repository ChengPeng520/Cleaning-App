package com.example.cleaningapp.cleaner.uistate

data class NotifyUiState(val notifyItems: List<NotifyItemUiState> = listOf())

data class NotifyItemUiState(
    val notifyId: Int,
    val notifyStatus: String,
    val notifyDate: String
)