package com.example.cleaningapp.customer.model

import androidx.annotation.VisibleForTesting
import java.io.Serializable
import java.sql.Date

data class Coupon (var id: Int, var type:Boolean, var discount: Double, var minCost: Int, var count: Int, var exp: String, var obtained: Boolean) : Serializable {
    val discountString: String
        get() = (discount*10).toInt().toString()
    val moneyString: String
        get() = discount.toInt().toString()
}