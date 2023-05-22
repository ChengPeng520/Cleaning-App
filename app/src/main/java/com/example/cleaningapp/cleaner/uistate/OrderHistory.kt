package com.example.cleaningapp.cleaner.uistate

data class OrderHistoryUiState(val orderHistoryItems: List<OrderHistoryItemUiState> = listOf())

data class OrderHistoryItemUiState(
    val id: Int,
    val date: String,
    val totalCount: Int,
    val image: Int,
    val name: String,
    val unitPrice: Int,
    val number: Int,
    val grossPrice: Int
)