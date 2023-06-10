package com.example.cleaningapp.backstage.complaint.model

import java.sql.Timestamp

data class BSCompOrderItem(
    val orderId: Int = 0,
    val customerName: String,
    val timeCreate: Timestamp,
    val cleanerName: String,
    val status: Int,
    val timeUpdate: Timestamp,
    val returnReason: String? = "",
    val bsComplainRemark: String? = "",
) : java.io.Serializable
