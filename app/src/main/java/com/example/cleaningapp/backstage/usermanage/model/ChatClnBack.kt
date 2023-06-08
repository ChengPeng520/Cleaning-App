package com.example.cleaningapp.backstage.usermanage.model

import java.sql.Timestamp

data class ChatClnBack(
    val chatClnBackId: Int,
    val cleanerId: Int ,
    val backstageId: Int ,
    val timeCreate: Timestamp
):java.io.Serializable


