package com.example.cleaningapp.customer.model

import android.graphics.Bitmap
import java.io.Serializable

data class Cleaner(
    val id: Int,
    val photo: Bitmap,
    val name: String,
    val gender: Int,
    val stars: Float,
    val completedOrderCount: Int,
    val phone: String,
    val introduction: String
) : Serializable