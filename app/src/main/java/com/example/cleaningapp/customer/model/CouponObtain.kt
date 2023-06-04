package com.example.cleaningapp.customer.model

import android.annotation.SuppressLint
import java.sql.Date
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

data class CouponObtain(
    var couponId: Int = 0,
    var couponName: String = "",
    var discountType: Boolean = false,
    var _discount: Double = 0.0,
    var minPrice: Int = 0,
    var _expiredDate: Long,
    var isOnReceive: Boolean = false
) {

    var discount = if(discountType) {
        _discount.roundToInt().toString()
    } else {
        (_discount * 10).toString()
    }

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