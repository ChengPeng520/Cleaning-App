package com.example.cleaningapp.cleaner.uistate

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import com.example.cleaningapp.share.ImageUtils
import java.io.Serializable
import java.sql.Timestamp

data class OrderHistoryItemUiState(
    val id: Int = 0,
    val date: Timestamp? = null,
    val totalCount: Int = 0,
    val image: ByteArray? = null,
    val name: String = "",
    val unitPrice: Int = 0,
    val number: Int = 0,
    val grossPrice: Int = 0,
    val isDelivered: Boolean = false,
    val isShipped: Boolean = false
) : Serializable {
    val orderTime: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            return dateFormat.format(date)
        }
    val productPhoto: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(image!!)
        }
}

data class OrderHistoryShopOrder(
    val shopOrderId: Int,
    val timeCreate: Timestamp,
    val isDelivered: Boolean,
    val isShipped: Boolean,
    val totalPrice: Int
)

data class CLProduct(
    val name: String,
    val price: Int,
    val count: Int,
    val photo: ByteArray
)

data class CheckShopOrder(
    val shopOrders: List<OrderHistoryShopOrder>,
    val cList: List<List<CLProduct>>
)