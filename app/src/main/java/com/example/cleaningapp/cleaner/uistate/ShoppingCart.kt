package com.example.cleaningapp.cleaner.uistate

import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils

data class ShoppingCartItemUiState(
    val shopOrderId: Int = 0,
    val productId: Int = 0,
    val photo: ByteArray,
    val name: String = "",
    val price: Int = 0,
    var count: Int = 0,
) {
    val productPhoto: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(photo)
        }
}

data class ShopOrder(
    val shopOrderId : Int = 0,
    val recieverName : String = "",
    val recieverPhone : String = "",
    val recieverAddress : String = "",
    val totalPrice : Int = 0
)
