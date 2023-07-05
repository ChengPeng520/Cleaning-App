package com.example.cleaningapp.customer.model

import android.graphics.Bitmap
import java.io.Serializable

data class Customer(
    var name: String = "",
    var photo: Bitmap? = null,
    var phone: String = "",
    var gender: Int = 0,
    var introduction: String = "",
) : Serializable

