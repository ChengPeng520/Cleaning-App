package com.example.cleaningapp.customer.model

import java.io.Serializable
import java.sql.Date

data class Coupon (var id: Int, var type: Int, var discount: Double, var minCost: Int, var count: Int, var exp: String, var obtained: Boolean) : Serializable {
    val discountString: String
        get() = discount.toInt().toString()
}