package com.example.cleaningapp.backstage.coupon


import android.annotation.SuppressLint
import java.io.Serializable
import java.sql.Timestamp
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
data class Coupon(
    val couponId: Int = 0,
    var couponName: String = "",
    var discount: Double = 0.0,
    var discountType: Boolean = false,
    var minPrice: Int = 0,
    var count: Int = 0,
    var expiredDate: Timestamp? = null,
    var isOnUsed: Boolean = false,
) : Serializable {
    val newDateFormat: String
        get() {
            expiredDate?.let {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                return dateFormat.format(it)
            }
            return ""
        }
}

