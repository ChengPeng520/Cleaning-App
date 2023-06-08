package com.example.cleaningapp.customer.detailed

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import java.io.Serializable
import java.sql.Date
import java.sql.Time
import java.sql.Timestamp

data class Order(
    val orderId: Int = 0,
    val customerId: Int = 0,
    val cleanerId: Int = 0,
    val cleanerName: String = "",
    val areaCity: String = "",
    val areaDistrict: String = "",
    val areaDetail: String = "",
    val dateOrdered: Date? = null,
    val timeOrderedStart: Time? = null,
    val timeOrderedEnd: Time? = null,
    val livingRoomSize: Int = 0,
    val kitchenSize: Int = 0,
    val bathRoomSize: Int = 0,
    val customerCouponId: Int = 0,
    val roomSize: Int = 0,
    val remark: String = "",
    val priceForCustomer: Int = 0,
    val originalPrice: Int = 0,
    val stars: Float = 0F,
    val commentCleaner: String = "",
    val status: Int = 0,
    val bsComplainRemark: String = "",
) : Serializable {
    val address: String
        get() {
            return "$areaCity$areaDistrict$areaDetail"
        }
    val cleaningRange: String
        get() {
            val stringBuilder = StringBuilder()
            if (livingRoomSize != 0) stringBuilder.append("客廳${livingRoomSize}坪")
            if (kitchenSize != 0 && livingRoomSize != 0) stringBuilder.append("\n廚房${kitchenSize}坪")
            else if (kitchenSize != 0) stringBuilder.append("廚房${kitchenSize}坪")
            if (bathRoomSize != 0 && (livingRoomSize != 0 || kitchenSize != 0)) stringBuilder.append(
                "\n廁所${bathRoomSize}坪"
            )
            else if (bathRoomSize != 0) stringBuilder.append("/n廁所${bathRoomSize}坪")
            if (roomSize != 0 && (livingRoomSize != 0 || kitchenSize != 0 || bathRoomSize != 0)) stringBuilder.append(
                "\n房間${roomSize}坪"
            )
            else if (roomSize != 0) stringBuilder.append("房間${roomSize}坪")
            return stringBuilder.toString()
        }
    val cleaningTime: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val sb = StringBuilder()
            val dateFormat = SimpleDateFormat("HH:mm")
            sb.append(dateFormat.format(timeOrderedStart)).append("-")
                .append(dateFormat.format(timeOrderedEnd))
            return sb.toString()
        }

    val platformFee: Int
        get() {
            return (originalPrice * 0.1).toInt()
        }
    val coupon: Int
        get() {
            return (priceForCustomer - originalPrice - platformFee)
        }
}