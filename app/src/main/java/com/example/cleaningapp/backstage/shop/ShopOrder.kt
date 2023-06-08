package com.example.cleaningapp.backstage.shop

import android.annotation.SuppressLint
import java.io.Serializable
import java.sql.Timestamp
import java.text.SimpleDateFormat

data class shopOrder(
    val shopOrderId:Int?,
    val cleanerId:Int?,
    val recieverName:String?,
    val recieverPhone:String?,
    val recieverAddress:String?,
    var totalPrice: Int?,
    var timeCreate:Timestamp?,
    var isChecked:Boolean?,
    var isDelivered:Boolean?,
    var isShipped:Boolean?,

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