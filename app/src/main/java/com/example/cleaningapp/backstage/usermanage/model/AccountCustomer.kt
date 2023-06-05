package com.example.cleaningapp.backstage.usermanage.model

import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils
import java.sql.Timestamp

data class AccountCustomer(
    var customerId: Int,
    var email: String,
    var name: String,
    var photo:ByteArray,
    var phone: String,
    var gender: Int,
    var introduction: String,
    var timeCreate: Timestamp,
    var timeUpdate: Timestamp,
    var certification: String,
    var suspend: Boolean
):java.io.Serializable {
    val userPhoto: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(photo)
        }
}