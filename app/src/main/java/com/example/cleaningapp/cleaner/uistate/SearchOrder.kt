package com.example.cleaningapp.cleaner.uistate

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import com.example.cleaningapp.share.ImageUtils
import java.io.Serializable
import java.sql.Date
import java.sql.Time
import java.text.ParseException
import java.util.*


class SearchOrder(
    var photo: ByteArray? = null,
    val orderId: Int = 0,
    val cleanerId: Int = 0,
    val dateOrdered: Date? = null,
    val timeOrderedStart: Time? = null,
    val timeOrderedEnd: Time? = null,
    val areaCity: String = "",
    val areaDistrict: String = "",
    val areaDetail: String = "",
    val livingRoomSize: Int = 0,
    val kitchenSize: Int = 0,
    val bathRoomSize: Int = 0,
    val roomSize: Int = 0,
    val remark: String = "",
    val priceForCleaner: Int = 0,
    var status: Int = 0
) {
    val customerPhoto: Bitmap?
        get() {
            return photo?.let { ImageUtils.bytesToBitmap(it) }
        }

    val orderStartDate: Calendar
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val dateTimeString = "${this.dateOrdered} ${this.timeOrderedStart}"
            return try {
                val date = dateFormat.parse(dateTimeString)
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = date.time
                calendar
            } catch (e: ParseException) {
                e.printStackTrace()
                // 處理日期解析錯誤的情況，例如回傳預設的 Calendar 物件
                Calendar.getInstance()
            }
        }

    val orderEndDate: Calendar
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val dateTimeString = "${this.dateOrdered} ${this.timeOrderedEnd}"
            return try {
                val date = dateFormat.parse(dateTimeString)
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = date.time
                calendar
            } catch (e: ParseException) {
                e.printStackTrace()
                // 處理日期解析錯誤的情況，例如回傳預設的 Calendar 物件
                Calendar.getInstance()
            }
        }

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
            if (timeOrderedStart == null || timeOrderedEnd == null) {
                return ""
            }
            val sb = StringBuilder()
            val dateFormat = SimpleDateFormat("HH:mm")
            sb.append(dateFormat.format(timeOrderedStart)).append("-")
                .append(dateFormat.format(timeOrderedEnd))
            return sb.toString()
        }

    data class ApplingOrders(
        val order: SearchOrder,
        val photo: ByteArray
    ) {

        val customerPhoto: Bitmap?
            get() {
                return ImageUtils.bytesToBitmap(photo)
            }
    }
}


data class OrderPhotos(
    val photos: List<ByteArray>
) {
    val photo1: Bitmap?
        get() {
            return if (photos.isNotEmpty()) {
                ImageUtils.bytesToBitmap(photos[0])
            } else null
        }
    val photo2: Bitmap?
        get() {
            return if (photos.size >= 2) {
                ImageUtils.bytesToBitmap(photos[1])
            } else null
        }
    val photo3: Bitmap?
        get() {
            return if (photos.size == 3) {
                ImageUtils.bytesToBitmap(photos[2])
            } else null
        }

}

data class SearchOrderPhotos(
    val order: SearchOrder,
    val photos: List<ByteArray>
)


data class CreateOrderPhotos(
    //  拍照功能
    var photo1: Bitmap? = null,
    var photo2: Bitmap? = null,
    var photo3: Bitmap? = null,
) : Serializable

class OrderForSearch {
    data class Order(
        val orderId: Int = 0,
        val dateOrdered: Date? = null,
        val timeOrderedStart: Time? = null,
        val timeOrderedEnd: Time? = null,
        val areaCity: String = "",
        val areaDistrict: String = "",
        val areaDetail: String = "",
        val livingRoomSize: Int = 0,
        val kitchenSize: Int = 0,
        val bathRoomSize: Int = 0,
        val roomSize: Int = 0,
        val remark: String = "",
        val priceForCleaner: Int = 0,
    ) {
        val orderStartDate: Calendar
            get() {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
                val dateTimeString = "${this.dateOrdered} ${this.timeOrderedStart}"
                return try {
                    val date = dateFormat.parse(dateTimeString)
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = date.time
                    calendar
                } catch (e: ParseException) {
                    e.printStackTrace()
                    // 處理日期解析錯誤的情況，例如回傳預設的 Calendar 物件
                    Calendar.getInstance()
                }
            }

        val orderEndDate: Calendar
            get() {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
                val dateTimeString = "${this.dateOrdered} ${this.timeOrderedEnd}"
                return try {
                    val date = dateFormat.parse(dateTimeString)
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = date.time
                    calendar
                } catch (e: ParseException) {
                    e.printStackTrace()
                    // 處理日期解析錯誤的情況，例如回傳預設的 Calendar 物件
                    Calendar.getInstance()
                }
            }

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
                if (timeOrderedStart == null || timeOrderedEnd == null) {
                    return ""
                }
                val sb = StringBuilder()
                val dateFormat = SimpleDateFormat("HH:mm")
                sb.append(dateFormat.format(timeOrderedStart)).append("-")
                    .append(dateFormat.format(timeOrderedEnd))
                return sb.toString()
            }

        data class ApplingOrder(
            val order: SearchOrder,
            val photo: ByteArray

        )
    }

}
