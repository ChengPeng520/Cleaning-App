package com.example.cleaningapp.customer.model

import java.io.Serializable

data class Order(
    val orderId: Int? = null,
    val county: String,
    val district: String,
    val address: String,
    val date: String,
    val timeBegin: String,
    val timeEnd: String,
    val livingRoomSize: Double = 0.0,
    val kitchenSize: Double = 0.0,
    val bathroomSize: Double = 0.0,
    val bedroomSize: Double = 0.0,
    val notes: String = "",
    val photo1: Int? = null,
    val photo2: Int? = null,
    val photo3: Int? = null,
    val price: Int,
    val discount: Int = 0,
) : Serializable {
    val livingRoomSizeString: String
        get() = livingRoomSize.toString()
    val kitchenSizeString: String
        get() = kitchenSize.toString()
    val bathroomSizeString: String
        get() = bathroomSize.toString()
    val bedroomSizeString: String
        get() = bedroomSize.toString()

}

