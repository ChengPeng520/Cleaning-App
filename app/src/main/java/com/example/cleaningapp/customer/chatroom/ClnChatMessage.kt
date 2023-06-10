package com.example.cleaningapp.customer.chatroom

data class ClnChatMessage(
    val msgCustClnId: Int = 0,
    val customerId: Int = 0,
    val cleanerId: Int = 0,
    val text: String = ""
)
