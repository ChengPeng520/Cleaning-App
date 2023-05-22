package com.example.cleaningapp.backstage.shop

import java.io.Serializable

class shopOrder(
    var num:String,
    var state:String,
    var time:String,
    var productSum: String,
    var totalmoney:String,
    var recipient:String,
    var phoneNumber:String,
    var shopAddress:String?,

): Serializable