package com.example.cleaningapp.backstage.usermanage.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.sql.Timestamp


data class AccountBackstage(
    val backstageId: Int,
    val account: String,
    val name: String,
    val timeCreate: Timestamp,
    val timeUpdate: Timestamp,
    val suspend: Boolean = false,
)