package com.example.cleaningapp.cleaner.uistate

import java.io.Serializable
import java.time.LocalDate

//Serializable 序列化
data class Job
//data class自動幫setter取質(容器=資料庫的欄位)
    (
    var imageId: Int,
    var name: String,
    var date: String,
    var localtion: String,
    var price: String
) : Serializable

//var date: LocalDate