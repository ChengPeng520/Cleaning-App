package com.example.cleaningapp.cleaner.uistate

import java.io.Serializable

    data class Work(
        var imageId: Int,
        var time: String,
        var localtion: String,
    ) : Serializable