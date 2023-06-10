package com.example.cleaningapp.backstage.shop

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.Serializable

data class OrderDetail(
    val shopOrderListId:Int?,
    val shopOrderId: Int?,
    val productId:Int?,
    val photo: ByteArray? = null,
    val price: Int?,
    var count: Int?,
    ): Serializable {
    val orderListPhoto: Bitmap?
        get() {
            return if (photo != null) {
                BitmapFactory.decodeByteArray(photo, 0, photo!!.size)
            } else {
                null
            }
        }
}

