package com.example.cleaningapp.customer.model

import java.io.Serializable

data class Coupon(
    var couponId: Int,
    var couponName: String,
    var discount: Double,
    var discountType: Boolean,
    var minPrice: Int,
    var expiredDate: String = "",
    var isOnUsed: Boolean = true
) : Serializable {
    var _discount = if(discountType) {
        discount.toInt().toString()
    } else {
        discount.toInt().toString()
    }

    val discountString: String
        get() = (discount * 10).toInt().toString()
    val moneyString: String
        get() = discount.toInt().toString()
}