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
    var livingRoomSize: Double = 0.0,
    var kitchenSize: Double = 0.0,
    var bathroomSize: Double = 0.0,
    var roomSize: Double = 0.0,
    var remark: String = "",
    val customerCouponId: Int? = null,
    val photo1: Bitmap? = null,
    val photo2: Bitmap? = null,
    val photo3: Bitmap? = null,
    var priceForCustomer: Int = 0
) : Serializable {
    var charge: Int = priceForCustomer * 0.1.toInt()
    var tvUseCoupon: String = ""
}

