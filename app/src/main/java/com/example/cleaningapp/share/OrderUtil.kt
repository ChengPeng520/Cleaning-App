package com.example.cleaningapp.share

import java.sql.Date
import java.sql.Time

class OrderUtil {
    data class Order(
        val orderId: Int = 0,
        val customerId: Int = 0,
        val cleanerId: Int = 0,
        val areaCity: String = "",
        val areaDistrict: String = "",
        val areaDetail: String = "",
        val dateOrdered: Date? = null,
        val timeOrderedStart: Time? = null,
        val timeOrderedEnd: Time? = null,
        val livingRoomSize: Int = 0,
        val kitchenSize: Int = 0,
        val bathRoomSize: Int = 0,
        val roomSize: Int = 0,
        val remark: String = "",
        val commentCleaner: String = "",
        val priceForCleaner: Int = 0,
        val stars: Float = 0f,
        val status: Int = 0
    )

    data class OrderStatus(
        val order: Order,
        val photo: List<ByteArray>?
    )
}