package com.example.cleaningapp.customer.csCreateOrder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.CreateOrderPhoto
import com.example.cleaningapp.customer.model.Order

class CsOrderConfirmedViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val photo: MutableLiveData<CreateOrderPhoto> by lazy { MutableLiveData<CreateOrderPhoto>() }

//    init {
//        loadOrder()
//    }
//
//    private fun loadOrder() {
//        order.value = com.example.cleaningapp.backstage.order.Order(
//            1,
//            "臺北市",
//            "中山區",
//            "南京東路三段219號5樓",
//            "2023/5/30",
//            "9:00",
//            "12:00",
//            10,
//            8,
//            0,
//            15,
//            "終於約到車輪餅妹妹了他明天就要來家裡玩了好緊張家裡亂七八糟希望趕快有人來幫我整理得亮晶晶香噴噴讓車輪餅妹妹愛上我",
//            R.drawable.before1,
//            R.drawable.before2,
//            100,
//            1500
//        )
//    }
}