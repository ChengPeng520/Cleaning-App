package com.example.cleaningapp.backstage.usermanage.model

import java.io.Serializable

class User(
    var id: Int,
    var avatar: Int,
    var email: String,
    var password: String,
    var name: String,
    var phone: String,
    var address: String,
    var gender: String,
    var createTime: String,
    var updateTime: String,
    var role: String,
    var suspend: String,
    var verify: String,
    var intro: String,
    var idNum: String,
    var bank: String,
    var idCardFront: Int,
    var idCardBack: Int,
    var criminalRecord: Int,
) : Serializable
