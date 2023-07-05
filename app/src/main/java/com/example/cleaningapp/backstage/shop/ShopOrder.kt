package com.example.cleaningapp.backstage.shop

import android.annotation.SuppressLint
import java.io.Serializable
import java.sql.Timestamp
import java.text.SimpleDateFormat

data class ShopOrder(
    val shopOrderId:Int? =0 ,
    val cleanerId:Int? =0,
    val recieverName:String? ="",
    val recieverPhone:String? = "",
    val recieverAddress:String? ="",
    var totalPrice: Int? = 0,
    var timeCreate:Timestamp? = null,
    var isChecked:Boolean? =false,
    var isDelivered:Boolean? = null,
    var isShipped:Boolean? = false,

): Serializable{
    val shopOderTime:String
    @SuppressLint("SimpleDateFormat")
    get(){
        timeCreate.let {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            return dateFormat.format(it)
        }
    }
}