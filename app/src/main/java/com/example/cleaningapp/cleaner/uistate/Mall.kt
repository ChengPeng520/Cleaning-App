package com.example.cleaningapp.cleaner.uistate

data class ProductUiState(val productItems: List<ProductItemUiState> = listOf())

data class ProductItemUiState(
    val id: Int = 0,
    val image: Int = 0,
    val name: String = "",
    val price: Int = 0
)
