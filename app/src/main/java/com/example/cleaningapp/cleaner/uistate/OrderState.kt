package com.example.cleaningapp.cleaner.uistate

data class OrderStateUiState(
    val orderId: Int = 0,
    val dateOrdered: String = "",
    val orderedTime: String = "",
    val address: String = "",
    val livingRoomSize: Int = 0,
    val kitchenSize: Int = 0,
    val bathRoomSize: Int = 0,
    val roomSize: Int = 0,
    val remark: String = "",
    val priceForCleaner: Int = 0,
    val status: Int = 0
)