package com.example.cleaningapp.backstage.shop

import java.io.Serializable

data class OrderDetail(
    var orderImage: Int,
    var productName: String,
    var productPrice: String,
    var productSum: String
    ): Serializable

