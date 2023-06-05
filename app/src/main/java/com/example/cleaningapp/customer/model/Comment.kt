package com.example.cleaningapp.customer.model

import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils
import java.io.Serializable

data class Comment(
    val cleanerId: Int,
    val name: String,
    val photo: ByteArray,
    val orderId: Int,
    val csID: Int,
    val stars: Float,
    val commentCleaner: String
) : Serializable {
    val customerPhoto: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(photo)
        }
}