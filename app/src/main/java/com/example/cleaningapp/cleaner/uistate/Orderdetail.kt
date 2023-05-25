package com.example.cleaningapp.cleaner.uistate

import java.io.Serializable

data class Orderdetail (
    val imgId:Int,
    val csname:String,
    val orderId: Int,
    val county: String,
    val district: String,
    val address: String,
    val date: String,
    val orderedTime: String,
    val timeBegin: String,
    val timeEnd: String,
    val livingRoomSize: Double = 0.0,
    val kitchenSize: Double = 0.0,
    val bathroomSize: Double = 0.0,
    val bedroomSize: Double = 0.0,
    val notes: String = "",
    val photo: Int? = null,
    val price: Int,
    val discount: Int = 0,
    ) : Serializable {

    }
