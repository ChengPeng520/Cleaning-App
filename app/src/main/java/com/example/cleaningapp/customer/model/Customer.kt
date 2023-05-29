package com.example.cleaningapp.customer.model

import android.graphics.Bitmap

data class Customer(
    val name: String = "",
    val photo: Bitmap? = null,
    val phone: String = "",
    val gender: Int = 0,
    val introduction: String = ""
)
