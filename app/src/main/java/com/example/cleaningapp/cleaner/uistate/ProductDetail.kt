package com.example.cleaningapp.cleaner.uistate

import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils

data class ProductDetailUiState(
    val productId: Int = 0,
    val photo: ByteArray? = null,
    val name: String = "",
    val description: String = "",
    val count: Int = 1,
    val price: Int = 0,
) {
    val productPhoto: Bitmap?
        get() {
            photo?.let {
                return ImageUtils.bytesToBitmap(photo)
            }
            return null
        }
    val totalPrice: Int
        get() {
            return count * price
        }
}

data class ShopOrderList(
    val shopOrderId: Int,
    val productId: Int,
    val count: Int,
    val price: Int
)
