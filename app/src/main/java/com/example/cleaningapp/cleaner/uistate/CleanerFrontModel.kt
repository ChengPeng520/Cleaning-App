package com.example.cleaningapp.cleaner.uistate

import java.io.Serializable
//Serializable 序列化
class CleanerFrontModel
    (var imageId: Int, var name: String, var date: String,var localtion:String,var price:Int) : Serializable