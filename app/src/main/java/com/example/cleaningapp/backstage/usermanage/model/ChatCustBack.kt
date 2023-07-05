package com.example.cleaningapp.backstage.usermanage.model

import java.sql.Timestamp

data class ChatCustBack(
    val chatCustBackId: Int,
    val customerId: Int,
    val backstageId: Int,
    val timeCreate: Timestamp,
):java.io.Serializable
