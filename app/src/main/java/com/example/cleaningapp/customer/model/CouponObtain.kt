package com.example.cleaningapp.customer.model

import android.annotation.SuppressLint
import java.sql.Date
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

data class CouponObtain(
    var couponId: Int,
    var couponName: String,
    var discountType: Boolean,
    var discount: Double,
    var minPrice: Int,
    var _expiredDate: Long,
    var isOnReceive: Boolean
) {

    var _discount = if (discountType) {
        discount.toInt().toString()
    } else {
        discount.toInt().toString()
    }
    val discountString: String
        get() = (discount * 10).toInt().toString()
    val discountMoney: Int
        get() = discount.toInt()

    var expiredDate = timeStampToDate(_expiredDate)

    @SuppressLint("SimpleDateFormat")
    private fun timeStampToDate(timestamp: Long): String {
        val date = Date(timestamp)
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(pattern)
        return simpleDateFormat.format(date)
    }

//    val discountString: String
//        get() = (_discount * 10).toInt().toString()
//    val moneyString: String
//        get() = _discount.toInt().toString()
}