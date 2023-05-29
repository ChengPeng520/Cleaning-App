package com.example.cleaningapp.backstage.usermanage.model

data class Chatroom(
    var chatId: Int,
    //使用ID判斷不同用戶資料
    var customerId: Int = 0,
    var cleanerId: Int = 0,
    var name: String,
    var avatar: Int,
    var email: String,
    var text: String,
    var createTime: String,
    var read: Boolean,
    var closed: Boolean,
) : java.io.Serializable
