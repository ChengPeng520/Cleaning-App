package com.example.cleaningapp.backstage.usermanage.model

import java.sql.Timestamp

data class Member(
    var id: Int,
    var email: String,
    var name: String,
    var timeCreate: Timestamp,
    var suspend: Boolean,
    var verify: Boolean,
    var status: Int,
): java.io.Serializable
