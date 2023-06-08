package com.example.cleaningapp.cleaner.uistate

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import com.example.cleaningapp.share.OrderUtil
import java.io.Serializable
import java.sql.Timestamp
import java.util.Calendar

//Serializable 序列化
data class Job
//data class自動幫setter取質(容器=資料庫的欄位)
    (
    val imgId: Int = 0,
    val csname: String = "",
    val orderId: Int = 0,
    val areaCity: String = "",
    val areaDistrict: String = "",
    val location: String = "",
    val areaDetail: String = "",
    val dateOrdered: String = "", // 刪除
    val orderedTime: String = "",
    val timeOrderedStart: String = "", // LocalDateTime
    val timeOrderedEnd: String = "", // LocalDateTime
    val livingRoomSize: Int = 0,
    val kitchenSize: Int = 0,
    val bathRoomSize: Int = 0,
    val roomSize: Int = 0,
    val remark: String = "",
    val photo: Int? = null,
    val photo2: Int? = null,
    val photo3: Int? = null,
    val priceForCleaner: Int = 0,
    val discount: Int = 0,
) : Serializable {
    val livingRoomSizeString: String
        get() = livingRoomSize.toString()
    val kitchenSizeString: String
        get() = kitchenSize.toString()
    val bathroomSizeString: String
        get() = bathRoomSize.toString()
    val bedroomSizeString: String
        get() = roomSize.toString()
    val orderStartDate: Calendar
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val date = dateFormat.parse("${this.dateOrdered} ${this.timeOrderedStart}")
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = date.time
            return calendar
        }
    val orderEndDate: Calendar
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val date = dateFormat.parse("${this.dateOrdered} ${this.timeOrderedEnd}")
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = date.time
            return calendar
        }
    data class OrderDetail(
        val order: Job,
        val photo: List<ByteArray>?
    )
    val address: String
        get() {
            return "$areaCity$areaDistrict$areaDetail"
        }
    val cleaningRange: String
        get() {
            val stringBuilder = StringBuilder()
            if (livingRoomSize != 0) stringBuilder.append("客廳${livingRoomSize}坪")
            if (kitchenSize != 0 && livingRoomSize == 0) stringBuilder.append("\n廚房${kitchenSize}坪")
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
    data class SearchOrderStatus(
        val order: OrderUtil.Order,
        val photo: List<ByteArray>?
    )
}

//var date: LocalDate