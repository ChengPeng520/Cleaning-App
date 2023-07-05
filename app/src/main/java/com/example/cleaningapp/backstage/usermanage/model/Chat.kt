package com.example.cleaningapp.backstage.usermanage.model

import java.sql.Timestamp

data class Chat(
    val chatItems: List<ChatItem> = mutableListOf()
)

data class ChatItem(
    val msgCustBackId: Int = 0,
    val customerId: Int = 0,
    val msgClnBackId: Int = 0,
    val cleanerId: Int = 0,
    val backstageId: Int = 1,
    val text: String = ""
)

data class MsgCustBack(
    val msgCustBackId: Int,
    val chatCustBackId: Int,
    val customerId: Int,
    val backstageId: Int,
    val text: String,
    val timeCreate: Timestamp,
)

data class MsgClnBack(
    val msgClnBackId: Int,
    val chatClnBackId: Int,
    val cleanerId: Int,
    val backstageId: Int,
    val text: String,
    val timeCreate: Timestamp,
)