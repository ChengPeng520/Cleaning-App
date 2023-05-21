package com.example.cleaningapp.cleaner.uistate

data class OrderChatroomUiState(
    val orderInfo: OrderInfo = OrderInfo(),
    val orderChatroomItems: List<OrderChatroomItemUiState> = listOf()
)

data class OrderInfo(
    val date: String = "",
    val hours: String = "",
    val area: String = "",
    val price: Int = 0
)

data class OrderChatroomItemUiState(
    val id: Int = 0,
    val fromId: Int = 0,
    val toId: Int = 0,
    val text: String = ""
)