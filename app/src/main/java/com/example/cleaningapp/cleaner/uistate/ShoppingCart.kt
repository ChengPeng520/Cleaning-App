package com.example.cleaningapp.cleaner.uistate

data class ShoppingCartUiState(
    val shoppingCartItems: List<ShoppingCartItemUiState> = listOf(),
    val grossPrice: Int = 0,
)

data class ShoppingCartItemUiState(
    val id: Int = 0,
    val image: Int = 0,
    val name: String = "",
    val unitPrice: Int = 0,
    val number: Int = 0
)
