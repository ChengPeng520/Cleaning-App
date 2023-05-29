package com.example.cleaningapp.cleaner.uistate

import java.io.Serializable
import java.time.LocalDate

//Serializable 序列化
data class Job
//data class自動幫setter取質(容器=資料庫的欄位)
    (
    val imgId:Int = 0,
    val csname:String = "",
    val orderId: String = "",
    val county: String = "",
    val district: String = "",
    val location: String ="",
    val address: String = "",
    val date: String = "",
    val orderedTime: String = "",
    val timeBegin: String = "",
    val timeEnd: String = "",
    val livingRoomSize:  Double = 0.0,
    val kitchenSize:  Double = 0.0,
    val bathroomSize: Double = 0.0,
    val bedroomSize:  Double = 0.0,
    val notes: String = "",
    val photo: Int? = null,
    val photo2: Int? = null,
    val photo3: Int? = null,
    val price: String = "",
    val discount: Int = 0,
):Serializable {
    val livingRoomSizeString: String
        get() = livingRoomSize.toString()
    val kitchenSizeString: String
        get() = kitchenSize.toString()
    val bathroomSizeString: String
        get() = bathroomSize.toString()
    val bedroomSizeString: String
        get() = bedroomSize.toString()
}

//var date: LocalDate