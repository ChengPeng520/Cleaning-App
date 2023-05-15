package com.example.cleaningapp.backstage.order

import java.io.Serializable

data class Order(
    var Number: String,
    var Status: String,
    var Date: String,
    var Cleantime: String,
    var Timetotal: Int,
    var Address: String,
    var Area: String,
    var Tools: String,
    var Focus: String,
    var Money: String,
    var BookingFee: String,
    var Coupon: String,
    var Total: String,
    var csPicture: Int,
    var csPicture2: Int,
    var csPicture3: Int,
    var clPicture: Int,
    var clPicture2: Int,
    var clPicture3: Int,

    ) : Serializable {


}
