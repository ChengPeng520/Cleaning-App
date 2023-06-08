package com.example.cleaningapp.customer.detailed

data class OrderInfo(
    val order: Order,
    val photos: List<ByteArray>?
)