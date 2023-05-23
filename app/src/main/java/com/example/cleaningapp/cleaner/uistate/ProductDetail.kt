package com.example.cleaningapp.cleaner.uistate

data class ProductDetailUiState(
    val id: Int = 0,
    val image: Int = 0,
    val name: String = "",
    val description: String = "",
    val count: Int = 1,
    val price: Int = 0,
    val totalPrice: Int = 0
)
