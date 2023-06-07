package com.example.cleaningapp.customer.detailed

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import java.io.Serializable
import java.sql.Date
import java.sql.Time
import java.sql.Timestamp

data class Order(
    val orderId: Int,
    val customerId: Int,
    val cleanerId: Int,
    val cleanerName: String,
    val areaCity: String,
    val areaDistrict: String,
    val areaDetail: String,
    val dateOrdered: Date,
    val timeOrderedStart: String,
    val timeOrderedEnd: String,
    val livingRoomSize: Int,
    val kitchenSize: Int,
    val bathRoomSize: Int,
    val customerCouponId: Int,
    val roomSize: Int,
    val remark: String,
    val priceForCustomer: Int,
    val originalPrice: Int,
    val stars: Double,
    val commentCustomer: String,
    val status: Int,
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
        get() {
            val sb = StringBuilder()
            val timeFormat = SimpleDateFormat("HH:mm")
            val startTime = timeFormat.format(timeFormat.parse(timeOrderedStart))
            val endTime = timeFormat.format(timeFormat.parse(timeOrderedEnd))
            sb.append("$startTime - $endTime")
            sb.append(" (${calculateCleaningHours()}小時)")
            return sb.toString()
        }

    private fun calculateCleaningHours(): Int {
        val startTime = timeOrderedStart.split(":").toTypedArray()
        val endTime = timeOrderedEnd.split(":").toTypedArray()
        val startHour = startTime[0].toInt()
        val startMinute = startTime[1].toInt()
        val endHour = endTime[0].toInt()
        val endMinute = endTime[1].toInt()
        val diffHour = endHour - startHour
        val diffMinute = endMinute - startMinute
        return if (diffMinute < 0) diffHour - 1 else diffHour
    }
}