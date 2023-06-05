package com.example.cleaningapp.cleaner.uistate

import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils

data class ShoppingCartUiState(val shoppingCartItems: List<ShoppingCartItemUiState> = listOf())

data class ShoppingCartItemUiState(
    val shopOrderId: Int = 0,
    val photo: ByteArray,
    val name: String = "",
    val price: Int = 0,
    val count: Int = 0,
    val totalPrice: Int = 0
) {
    val productPhoto: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(photo)
        }
}
