package com.example.cleaningapp.customer.model

import java.io.Serializable

data class Cv(val cleanerId: Int, val name: String,val img: Int, val orderId: Int,val csID: Int, val stars: Float):
    Serializable {

}