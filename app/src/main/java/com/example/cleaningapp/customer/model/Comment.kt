package com.example.cleaningapp.customer.model

import java.io.Serializable

data class Comment(val cleanerId: Int, val name: String,val csImg: Int, val orderId: Int,val csID: Int, val stars: Float, val comment: String):
    Serializable {

    }