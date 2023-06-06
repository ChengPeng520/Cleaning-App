package com.example.cleaningapp.backstage.shop


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.io.Serializable
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.logging.SimpleFormatter

data class Product(
    val productId :Int = 0,
    var photo: ByteArray? = null,
    var name: String? = "",
    var price: Int? = 0,
    var description: String? = "",
    var storage: Int? = 0,
    var timeCreate:Timestamp? =null,
    var timeUpdate:Timestamp? =null,
    var isOnSale:Boolean? = false


) : Serializable{
    val photoBitmap : Bitmap?
    get() {
        return if (photo != null){
            BitmapFactory.decodeByteArray(photo,0,photo!!.size)
        }else{
            null
        }
    }
    val newTimeCreate: String
        @SuppressLint("SimpleDateFormat")
        get(){
            timeCreate?.let {
                val dataFormat = SimpleDateFormat("yyyy-MM-dd")
                return  dataFormat.format(it)
            }
            return ""
        }
    val newTimeUpdate:String
        @SuppressLint("SimpleDateFormat")
        get(){
            timeUpdate?.let {
                val dateFormat =SimpleDateFormat("yyyy-MM-dd")
                return dateFormat.format(it)
            }
            return  ""
        }



}
//建立商品屬性
