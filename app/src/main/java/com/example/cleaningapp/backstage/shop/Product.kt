package com.example.cleaningapp.backstage.shop


import java.io.Serializable

class Product(
    var image: Int,
    var productname: String,
    var pruductprice: String,
    var Description: String,
    var inventory : String,
    var addDate:String,
    var upDate:String,
) : Serializable

//建立商品屬性
