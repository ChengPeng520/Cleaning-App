package com.example.cleaningapp.backstage.complaint.model

import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils

data class BSCompOrderInfo(
    var bsCompOrder: BSCompOrder,
    var orderPhotos: List<ByteArray>?
)

data class OrderPhotos(
    val photos: List<ByteArray>
) {
    val photo1: Bitmap?
        get() {
            return if (photos.isNotEmpty()) {
                ImageUtils.bytesToBitmap(photos[0])
            } else null
        }
    val photo2: Bitmap?
        get() {
            return if (photos.size >= 2) {
                ImageUtils.bytesToBitmap(photos[1])
            } else null
        }
    val photo3: Bitmap?
        get() {
            return if (photos.size == 3) {
                ImageUtils.bytesToBitmap(photos[2])
            } else null
        }
}
