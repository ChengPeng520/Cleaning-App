package com.example.cleaningapp.backstage.coupon

import android.widget.Switch
import java.io.Serializable

class Coupon(
    var num: String,
    var name: String,
    var percentage: String,
    var discount: String,
    var quantity: String,
    var limit: String,
    var minimum:String,
    var switch: Boolean,

) : Serializable
