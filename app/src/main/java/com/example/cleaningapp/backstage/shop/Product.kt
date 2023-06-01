package com.example.cleaningapp.backstage.shop


import java.io.Serializable

class Product(
    var image: Int,
    var productname: String = "",
    var price: Int = 0,
    var Description: String = "",
    var inventory : Int = 0,
    var addDate:String ="",
    var upDate:String = "",
) : Serializable

//建立商品屬性
