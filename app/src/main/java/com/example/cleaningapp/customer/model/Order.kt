package com.example.cleaningapp.customer.model

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import java.io.Serializable

data class Order(
    val customerId: Int = 0,
    var areaCity: String = "",
    var areaDistrict: String = "",
    var areaDetail: String = "",
    var dateOrdered: String = "",
    var timeOrderedStart: String = "",
    var timeOrderedEnd: String = "",
    var livingRoomSize: Int = 0,
    var kitchenSize: Int = 0,
    var bathRoomSize: Int = 0,
    var roomSize: Int = 0,
    var remark: String = "",
    val customerCouponId: Int? = null,
    var couponDiscount: Int = 0,
    var originalPrice: Int = 0,
    var priceForCustomer: Int = 0
) : Serializable {
    val charge: Int
        get() {
            return (originalPrice * 0.1).toInt()
        }
    var tvUseCoupon: String = ""
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
}

data class CreateOrderPhoto(
    //  拍照功能
    var photo1: Bitmap? = null,
    var photo2: Bitmap? = null,
    var photo3: Bitmap? = null,
) : Serializable

data class OrderRemind(
    var orderId: Int = 0,
    var areaCity:String = "",
    var areaDistrict: String = "",
    var dateOrdered: String = "",
    var timeOrderedStart: String = "",
    var timeOrderedEnd: String = "",
)

data class OrderEstablished (
    var orderId: Int = 0,
    var cleanerId: Int = 0
)