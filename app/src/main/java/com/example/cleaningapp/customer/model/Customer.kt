package com.example.cleaningapp.customer.model

import android.graphics.Bitmap
import java.io.Serializable

data class Customer(
    val name: String = "",
    val photo: Bitmap? = null,
    val phone: String = "",
    val gender: Int = 0,
    val introduction: String = ""
): Serializable {
}

