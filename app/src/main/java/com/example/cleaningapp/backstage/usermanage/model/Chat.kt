package com.example.cleaningapp.backstage.usermanage.model

import java.sql.Timestamp

class Chat(
    var chatId: Int,
    var name: String,
    var avatar: Int,
    var email: String,
    var text: String,
    var createTime: String,
    var read: Boolean,
    var closed: Boolean
): java.io.Serializable