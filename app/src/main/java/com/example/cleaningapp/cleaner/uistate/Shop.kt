package com.example.cleaningapp.cleaner.uistate

data class ProductsUiState(val products: List<ProductsItemUiState> = listOf())

data class ProductsItemUiState(
    val id: Int,
    val image: Int,
    val name: String,
    val price: Int
)
