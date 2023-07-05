package com.example.cleaningapp.customer.model

import java.io.Serializable

data class Coupon(
    val customerCouponId: Int,
    val couponId: Int,
    val couponName: String,
    val discount: Double,
    val discountType: Boolean,
    val minPrice: Int,
    val expiredDate: String = "",
    val isOnUsed: Boolean = true
) : Serializable {
    val discountString: String
        get() = (discount * 10).toInt().toString()
    val discountMoney: Int
        get() = discount.toInt()
}