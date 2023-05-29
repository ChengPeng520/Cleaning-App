package com.example.cleaningapp.customer.model

import android.graphics.Bitmap
import java.io.Serializable

data class Order(
    var areaCity: String = "",
    var areaDistrict: String = "",
    var areaDetail: String = "",
    var dateOrdered: String = "",
    var timeOrderedStart: String = "",
    var timeOrderedEnd: String = "",
    var livingRoomSize: String = "",
    var kitchenSize: String = "",
    var bathroomSize: String = "",
    var roomSize: String = "",
    var remark: String = "",
    val customerCoupon: Coupon? = null,
    val photo1: Bitmap? = null,
    val photo2: Bitmap? = null,
    val photo3: Bitmap? = null,
    var price: String = "",
    val discount: Int = 0,
) : Serializable {
    val charge: Int
        get() = price.toInt() * 0.1.toInt()
}

