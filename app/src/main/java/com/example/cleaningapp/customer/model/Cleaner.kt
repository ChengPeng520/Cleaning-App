package com.example.cleaningapp.customer.model

import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils
import java.io.Serializable

data class Cleaner(
    val cleanerId: Int,
    val photo: ByteArray,
    val name: String,
    val gender: Int,
    val averageStars: Float,
    val count: Int,
    val phone: String,
    val introduction: String
) : Serializable {
    val cleanerPhoto: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(photo)
        }
}