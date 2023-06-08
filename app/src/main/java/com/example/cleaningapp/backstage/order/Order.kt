package com.example.cleaningapp.backstage.order

import android.annotation.SuppressLint
import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils
import java.io.Serializable
import java.sql.Date
import java.sql.Time
import java.text.SimpleDateFormat


data class Order(
    val orderId: Int? = null,
    val customerId: Int? = null,
    val cleanerId: Int? = null,
    val areaCity: String? = "",
    val areaDistrict: String? = "",
    val areaDetail: String? = "",
    val dateOrdered: Date? = null,
    val timeOrderedStart: Time? = null,
    val timeOrderedEnd: Time? = null,
    val livingRoomSize: Int = 0,
    val kitchenSize: Int = 0,
    val bathRoomSize: Int = 0,
    val roomSize: Int = 0,
    val remark: String = "",
    val customerCouponId: Int = 0,
    val priceForCustomer: Int = 0,
    val originalPrice: Int = 0,
    val priceForCleaner: Int = 0,
    val status: Int = 0,
) : Serializable {
    val couponPrice:Int?
    get() {

        val result = priceForCustomer - priceForCleaner - originalPrice
        return result
    }
    val cleanTotalTime: String?
        @SuppressLint("SimpleDateFormat")
        get() {
            val range = StringBuilder()
            val dateFormat = SimpleDateFormat("HH:mm")
            range.append(timeOrderedStart?.let { dateFormat.format(it) }).append("-").append(
                timeOrderedEnd?.let { dateFormat.format(it) }
            )
            return range.toString()
        }

    val address: String
        get() {
            return "$areaCity$areaDistrict$areaDetail"
        }
    val cleanRange: String
        get() {
            val stringBuilder = StringBuilder()
            if (livingRoomSize != 0) stringBuilder.append("客廳${livingRoomSize}坪")
            if (livingRoomSize != 0 && kitchenSize != 0) stringBuilder.append("\\ 廚房${kitchenSize}坪")
            else if (kitchenSize != 0) stringBuilder.append("廚房${kitchenSize}坪")
            if (roomSize != 0 && (livingRoomSize != 0 || kitchenSize != 0)) stringBuilder.append(
                "\\房間 ${roomSize}坪"
            )
            else if (roomSize != 0) stringBuilder.append("房間${roomSize}坪")
            if (bathRoomSize != 0 && (livingRoomSize != 0 || kitchenSize != 0 || roomSize != 0)) stringBuilder.append(
                "\\廁所${bathRoomSize}坪"
            )
            else if (bathRoomSize != 0) stringBuilder.append("廁所${bathRoomSize}坪")
            return stringBuilder.toString()
        }
    val searchOrderId: String
        get() {
            return orderId.toString()
        }
    val getStatus:String?
    get() {
        return when(status){
            in 0..3 -> "處理中"
            in 4..9 -> "已完成"
            else -> ""
        }
    }
}

data class CleaningPhotos(
    val cleaningPhotos: List<Bitmap?>,
) {
    val cleaningPhotos1: Bitmap?
        get() {
            return if (cleaningPhotos.isNotEmpty()) {
                cleaningPhotos[0]
            } else null
        }
    val cleaningPhotos2: Bitmap?
        get() {
            return if (cleaningPhotos.size >= 2) {
                cleaningPhotos[1]
            } else null
        }
    val cleaningPhotos3: Bitmap?
        get() {
            return if (cleaningPhotos.size == 3) {
                cleaningPhotos[2]
            } else null
        }

}

data class OrderPhotos(
    val orderPhotos: List<Bitmap?>,
) {
    val orderPhoto1: Bitmap?
        get() {
            return if (orderPhotos.isNotEmpty()) {
                orderPhotos[0]
            } else null
        }
    val orderPhotos2: Bitmap?
        get() {
            return if (orderPhotos.size >= 2) {
                orderPhotos[1]
            } else null
        }
    val orderPhotos3: Bitmap?
        get() {
            return if (orderPhotos.size == 3) {
                orderPhotos[2]
            } else null
        }

}

data class BackstageOrderInfo(
    val order: Order,
    val cleaningPhotos: List<ByteArray>,
    val orderPhotos: List<ByteArray>,
):Serializable{

}






