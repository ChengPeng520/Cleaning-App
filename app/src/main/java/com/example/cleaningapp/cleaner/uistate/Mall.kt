package com.example.cleaningapp.cleaner.uistate

import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils

data class ProductUiState(val productItems: List<ProductItemUiState> = listOf())

data class ProductItemUiState(
    val productId: Int = 0,
    val photo: ByteArray,
    val name: String = "",
    val price: Int = 0,
    val isOnSale: Boolean = false
) {
    val productPhoto: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(photo)
        }
}