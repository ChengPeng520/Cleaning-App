package com.example.cleaningapp.customer.model

import java.io.Serializable

data class Coupon(
    var couponId: Int,
    var couponName: String,
    var _discount: Double,
    var discountType: Boolean,
    var minPrice: Int,
    var expiredDate: String = "",
    var isOnUsed: Boolean = true
) : Serializable {

    var discount = if(discountType) {
        _discount.toInt().toString()
    } else {
        _discount.toInt().toString()
    }

    val discountString: String
        get() = (_discount * 10).toInt().toString()
    val moneyString: String
        get() = _discount.toInt().toString()
}