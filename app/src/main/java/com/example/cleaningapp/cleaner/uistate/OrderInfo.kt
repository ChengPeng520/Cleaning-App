package com.example.cleaningapp.cleaner.uistate

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import com.example.cleaningapp.share.ImageUtils
import java.sql.Timestamp

data class OrderInfoUiState(
    val date: Timestamp,
    val orderInfoItems: List<OrderInfoItemUiState>,
    val grossPrice: Int,
    val isDelivered: Boolean = false,
    val isShipped: Boolean = false
) {
    val orderTime: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            return dateFormat.format(date)
        }
}

data class OrderInfoItemUiState(
    val productId: Int,
    val photo: ByteArray,
    val name: String,
    val price: Int,
    val count: Int,
) {
    val productPhoto: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(photo)
        }
}
