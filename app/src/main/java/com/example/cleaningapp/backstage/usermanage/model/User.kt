package com.example.cleaningapp.backstage.usermanage.model

import android.graphics.Bitmap
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
    var timeCreate: Timestamp? = null,
    var timeUpdate: Timestamp? = null,
    var role: String = "",
    var suspend: Boolean = false,
    var verify: Boolean = false,
    var identifyNumber: String = "",
    var idCardFront: Bitmap? = null,
    var idCardBack: Bitmap? = null,
    var crc: Bitmap? = null,
) : Serializable {
    var accountId: Int? = null
        get() {
        if (cleanerId != null){
            return cleanerId
        }
        if (customerId != null){
            return customerId
        }
        return null
    }

    val userGender: String
        get() {
            return if (gender == 0) {
                "男"
            } else {
                "女"
            }
        }

}

