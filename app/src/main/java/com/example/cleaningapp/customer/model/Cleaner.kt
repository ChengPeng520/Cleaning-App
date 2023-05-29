package com.example.cleaningapp.customer.model

import java.io.Serializable

data class Cleaner(
    val id: Int,
    val image: Int,
    val name: String,
    val gender: Boolean,
    val averageStar: Float,
    val completedOrderCount: Int,
    val phone: String,
    val address: String,
    val intro: String
) : Serializable