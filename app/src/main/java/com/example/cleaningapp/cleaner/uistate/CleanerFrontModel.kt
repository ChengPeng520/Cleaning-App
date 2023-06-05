package com.example.cleaningapp.cleaner.uistate

import android.icu.text.SimpleDateFormat
import java.io.Serializable
import java.sql.Timestamp
import java.util.Calendar

//Serializable 序列化
data class Job
//data class自動幫setter取質(容器=資料庫的欄位)
    (
    val imgId: Int = 0,
    val csname: String = "",
    val orderId: String = "",
    val county: String = "",
    val district: String = "",
    val location: String = "",
    val address: String = "",
    val date: String = "", // 刪除
    val orderedTime: String = "",
    val timeBegin: String = "", // LocalDateTime
    val timeEnd: String = "", // LocalDateTime
    val livingRoomSize: Double = 0.0,
    val kitchenSize: Double = 0.0,
    val bathroomSize: Double = 0.0,
    val bedroomSize: Double = 0.0,
    val notes: String = "",
    val photo: Int? = null,
    val photo2: Int? = null,
    val photo3: Int? = null,
    val price: String = "",
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
    val orderStartDate: Calendar
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val date = dateFormat.parse("${this.date} ${this.timeBegin}")
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = date.time
            return calendar
        }
    val orderEndDate: Calendar
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val date = dateFormat.parse("${this.date} ${this.timeEnd}")
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = date.time
            return calendar
        }
}

//var date: LocalDate