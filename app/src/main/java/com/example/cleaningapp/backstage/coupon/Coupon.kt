package com.example.cleaningapp.backstage.coupon

import android.widget.Button
import java.io.Serializable

class Coupon(
    var num: String,
    var name: String,
    var percentage: String,
    var discount: String,
    var quantity: Int,
    var limit: String,
    var minimum:String,

) : Serializable
