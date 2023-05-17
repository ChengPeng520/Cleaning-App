package com.example.cleaningapp.cleaner.uistate

data class OrderInfoUiState(
    val date: String,
    val state: String,
    val orderInfoItems: List<OrderInfoItemUiState>,
    val grossPrice: Int
)

data class OrderInfoItemUiState(
    val id: Int,
    val image: Int,
    val name: String,
    val unitPrice: Int,
    val number: Int,
)
