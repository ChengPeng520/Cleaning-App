package com.example.cleaningapp.customer.detailed

import java.io.Serializable
import java.sql.Date
import java.sql.Timestamp

data class Order(
    val date: String,
    val time: String,
    val address: String,
    val size: String,
    val cleanerid: Int,
    val total: Int,

    val orderid: Int,
    val customerId: Int,
    val cleanerId: Int,
    val areaCity: String,
    val areaDistrict: String,
    val areaDetail: String,
    val dateOrdered: Date,
    val timeOrderedStart: Timestamp,
    val timeOrderedEnd: Timestamp,
    val livingroomSize: Int,
    val kitchenSize: Int,
    val bathroomSize: Int,
    val roomSize: Int,
    val remark: String,
    val customerCouponId: Int,
    val priceForCustomer: Int,
    val priceForCleaner: Int,
    val originalPrice: Int,
    val commentCleaner: String,
    val stars: Double,
    val returnReason: String,
    val bsComplainRemark: String,
    val status: Int,
) : Serializable