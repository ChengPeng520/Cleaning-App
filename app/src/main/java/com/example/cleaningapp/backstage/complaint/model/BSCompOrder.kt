package com.example.cleaningapp.backstage.complaint.model

import java.sql.Timestamp

data class BSCompOrder(
    var orderId: Int = 0,
    val customerName: String,
    val timeCreate: Timestamp,
    val cleanerName: String,
    var status: Int,
    val timeUpdate: Timestamp,
    var returnReason: String? = "",
    var bsComplainRemark: String? = "",
) : java.io.Serializable
