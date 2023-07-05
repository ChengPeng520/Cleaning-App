package com.example.cleaningapp.backstage.usermanage.model

import android.graphics.Bitmap
import com.example.cleaningapp.share.ImageUtils

data class ChatData(
    val chatCustBackId: Int? = null,
    val chatClnBackId: Int? = null,
    val customerId: Int? = null,
    val customerEmail: String? = null,
    val customerPhoto: ByteArray?,
    val cleanerId: Int? = null,
    val cleanerEmail: String? = null,
    val cleanerPhoto: ByteArray?,
    val text: String,
    val createTime: String
) : java.io.Serializable {
    val accountId: Int?
        get() {
            if (customerId != null) {
                return customerId
            }
            if (cleanerId != null) {
                return cleanerId
            }
            return null
        }

    var chatroomId: Int? = null
        get() {
            if (chatCustBackId != null) {
                return chatCustBackId
            } else if (chatClnBackId != null) {
                return chatClnBackId
            }
            return null
        }

    val photo: Bitmap?
        get() {
            if (customerPhoto != null) {
                return photoCustomer as Bitmap
            } else if (cleanerPhoto != null) {
                return photoCleaner as Bitmap
            }
            return null
        }
    val photoCustomer: Bitmap?
        get() {
            if (customerPhoto != null) {
                return ImageUtils.bytesToBitmap(customerPhoto)
            }
            return null
        }
    val photoCleaner: Bitmap?
        get() {
            if (cleanerPhoto != null) {
                return ImageUtils.bytesToBitmap(cleanerPhoto)
            }
            return null
        }

    val email: String?
        get() {
            if (customerEmail != null) {
                return customerEmail
            }
            if (cleanerEmail != null) {
                return cleanerEmail
            }
            return null
        }

    val time: String?
        get() {
            val inputString = createTime
            val timePattern = Regex("""\d{2}:\d{2}""")
            val matchResult = timePattern.find(inputString)
            return matchResult?.value
        }
}

