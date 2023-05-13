package com.example.cleaningapp.customer.detailed

data class Order(
    val status: Int,
    val orderId: Int,
    val date: String,
    val time: String,
    val address:String,
    val size: String,
    val remark: String,
    val cleaner: String,
    val total: Int)
