package com.example.cleaningapp.customer.model

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.sql.Date
import java.sql.Time

data class Order(
    val customerId: Int = 0,
    var areaCity: String = "",
    var areaDistrict: String = "",
    var areaDetail: String = "",
    var dateOrdered: Date? = null,
    var timeOrderedStart: Time? = null,
    var timeOrderedEnd: Time? = null,
    var livingRoomSize: Int = 0,
    var kitchenSize: Int = 0,
    var bathRoomSize: Int = 0,
    var roomSize: Int = 0,
    var remark: String = "",
    val customerCouponId: Int? = null,
    var couponDiscount: Int = 0,
    var originalPrice: Int = 0,
    var priceForCustomer: Int = 0,
) : Serializable {
    val orderDate: String
        get() {
            dateOrdered?.let {
                return it.toString()
            }
            return ""
        }
    val timeStart: String
        get() {
            timeOrderedStart?.let {
                return it.toString()
            }
            return ""
        }
    val timeEnd: String
        get() {
            timeOrderedEnd?.let {
                return it.toString()
            }
            return ""
        }
    val couponDisplay: String
        get() {
            return "- $couponDiscount"
        }
    val charge: Int
        get() {
            return (originalPrice * 0.1).toInt()
        }
    val address: String
        get() {
            return "$areaCity$areaDistrict$areaDetail"
        }

    val cleaningRange: String
        get() {
            val stringBuilder = StringBuilder()
            if (livingRoomSize != 0) stringBuilder.append("客廳${livingRoomSize}坪")
            if (kitchenSize != 0) stringBuilder.append("\n廚房${kitchenSize}坪")
            if (bathRoomSize != 0) stringBuilder.append("\n廁所${bathRoomSize}坪")
            if (roomSize != 0) stringBuilder.append("\n房間${roomSize}坪")
            return stringBuilder.toString()
        }
}

data class CreateOrder(
    var customerId: Int = 0,
    var areaCity: String = "",
    var areaDistrict: String = "",
    var areaDetail: String = "",
    var dateOrdered: Date? = null,
    var timeOrderedStart: String? = null,
    var timeOrderedEnd: String? = null,
    var livingRoomSize: Int = 0,
    var kitchenSize: Int = 0,
    var bathRoomSize: Int = 0,
    var roomSize: Int = 0,
    var remark: String? = null,
    var customerCouponId: Int? = null,
    var couponDiscount: Int = 0,
    var originalPrice: Int = 0,
    var priceForCustomer: Int = 0,
) : Serializable {
    val orderDate: String
        get() {
            dateOrdered?.let {
                return it.toString()
            }
            return ""
        }
    val timeStart: String
        get() {
            timeOrderedStart?.let {
                return it.toString()
            }
            return ""
        }
    val timeEnd: String
        get() {
            timeOrderedEnd?.let {
                return it.toString()
            }
            return ""
        }
    val couponDisplay: String
        get() {
            return "- $couponDiscount"
        }
    val charge: Int
        get() {
            return (originalPrice * 0.1).toInt()
        }
    val address: String
        get() {
            return "$areaCity$areaDistrict$areaDetail"
        }

    val cleaningRange: String
        get() {
            val stringBuilder = StringBuilder()
            if (livingRoomSize != 0) stringBuilder.append("客廳${livingRoomSize}坪")
            if (kitchenSize != 0 && livingRoomSize != 0) stringBuilder.append("\n廚房${kitchenSize}坪")
            else if (kitchenSize != 0) stringBuilder.append("廚房${kitchenSize}坪")
            if (bathRoomSize != 0 && (livingRoomSize != 0 || kitchenSize != 0)) stringBuilder.append(
                "\n廁所${bathRoomSize}坪"
            )
            else if (bathRoomSize != 0) stringBuilder.append("/n廁所${bathRoomSize}坪")
            if (roomSize != 0 && (livingRoomSize != 0 || kitchenSize != 0 || bathRoomSize != 0)) stringBuilder.append(
                "\n房間${roomSize}坪"
            )
            else if (roomSize != 0) stringBuilder.append("房間${roomSize}坪")
            return stringBuilder.toString()
        }
    val remarkDefault: String
        get() {
            remark?.let {
                return it
            }
            return "無"
        }
}

data class CreateOrderPhoto(
    //  拍照功能
    @Transient
    var photo1: Bitmap? = null,
    @Transient
    var photo2: Bitmap? = null,
    @Transient
    var photo3: Bitmap? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readParcelable(Bitmap::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(photo1, flags)
        parcel.writeParcelable(photo2, flags)
        parcel.writeParcelable(photo3, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CreateOrderPhoto> {
        override fun createFromParcel(parcel: Parcel): CreateOrderPhoto {
            return CreateOrderPhoto(parcel)
        }

        override fun newArray(size: Int): Array<CreateOrderPhoto?> {
            return arrayOfNulls(size)
        }
    }
}

data class OrderRemind(
    var orderId: Int = 0,
    var areaCity: String = "",
    var areaDistrict: String = "",
    var dateOrdered: Date? = null,
    var timeOrderedStart: Time? = null,
    var timeOrderedEnd: Time? = null,
    var status: Int = 0
) {
    val orderDate: String
        get() {
            dateOrdered?.let {
                return it.toString()
            }
            return ""
        }
    val time: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val sb = StringBuilder()
            val dateFormat = SimpleDateFormat("HH:mm")
            timeOrderedStart?.let {
                sb.append(dateFormat.format(timeOrderedStart)).append("-")
                    .append(dateFormat.format(timeOrderedEnd))
                return sb.toString()
            }
            return ""
        }
}

data class OrderEstablished(
    var orderId: Int = 0,
    var cleanerId: Int = 0
)

data class EstablishOrder(
    val order: CreateOrder,
    val photos: List<ByteArray>?
)
