package com.example.cleaningapp.backstage.usermanage.model

import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils
import java.io.Serializable
import java.sql.Timestamp

data class User(
    var cleanerId: Int? = null,
    var customerId: Int? = null,
    var backstageId: Int? = null,
    var email: String = "",
    var name: String = "",
    var photo: Bitmap? = null,
    var phone: String = "",
    var gender: Int = 0,
    var introduction: String? = "",
    var timeCreate: Timestamp,
    var timeUpdate: Timestamp,
    var role: String= "",
    var suspend: Boolean = false,
    var verify: Boolean = false,
    var identifyNumber: String = "",
    var idCardFront: Bitmap? = null,
    var idCardBack: Bitmap? = null,
    var crc: Bitmap? = null,
) : Serializable {
//    val userPhoto: Bitmap?
//        get() {
//            return ImageUtils.bytesToBitmap(photo!!)
//        }

    var userGender: String? = ""
        get() {
            return if (gender == 0) {
                "男"
            } else {
                "女"
            }
        }

//    val userIdCardFront: Bitmap?
//        get() {
//            idCardFront?.let {
//                return ImageUtils.bytesToBitmap(it)
//            }
//            return null
//        }
//
//    val userIdCardBack: Bitmap?
//        get() {
//            idCardBack?.let {
//                return ImageUtils.bytesToBitmap(it)
//            }
//            return null
//        }
//
//    val userCrc: Bitmap?
//        get() {
//            idCardBack?.let {
//                return ImageUtils.bytesToBitmap(it)
//            }
//            return null
//        }
}

