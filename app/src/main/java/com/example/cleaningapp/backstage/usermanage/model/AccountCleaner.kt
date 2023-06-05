package com.example.cleaningapp.backstage.usermanage.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.cleaningapp.share.ImageUtils
import java.sql.Timestamp

data class AccountCleaner(
    var cleanerId: Int,
    var email: String,
    var name: String,
    var photo: ByteArray,
    var phone: String,
    var gender: Int,
    var introduction: String,
    var timeCreate: Timestamp,
    var timeUpdate: Timestamp,
    var certification: String,
    var suspend: Boolean,
    var verify: Boolean,
    var identifyNumber: String,
    var idCardFront: ByteArray,
    var idCardBack: ByteArray,
    var crc: ByteArray
) : java.io.Serializable {
    val userPhoto: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(photo)
        }

    val userIdCardFront: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(idCardFront)
        }

    val userIdCardBack: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(idCardBack)
        }

    val userCrc: Bitmap?
        get() {
            return ImageUtils.bytesToBitmap(crc)
        }
}