package com.example.cleaningapp.cleaner.uistate

import android.graphics.Bitmap

data class CleanerMemberInfoUiState(
    var photo: Bitmap? = null,
    var name: String = "",
    var gender: Int = 0,
    var identifyNumber: String = "",
    var phone: String = "",
    var introduction: String = "",
    var idCardFront: Bitmap? = null,
    var idCardBack: Bitmap? = null,
    var crc: Bitmap? = null
)