package com.example.cleaningapp.cleaner.uistate

data class ProductUiState(val products: List<ProductItemUiState> = listOf())

data class ProductItemUiState(
    val id: Int,
    val image: Int,
    val name: String,
    val price: Int
)
