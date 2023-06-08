package com.example.cleaningapp.customer.csCreateOrder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.CreateOrderPhoto
import com.example.cleaningapp.customer.model.Order
import com.example.cleaningapp.customer.model.OrderEstablished
import com.example.cleaningapp.share.requestTask

class CsOrderConfirmedViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val photo: MutableLiveData<CreateOrderPhoto> by lazy { MutableLiveData<CreateOrderPhoto>() }


//    fun checkout(): Boolean {
//        requestTask<OrderEstablished>(
//            "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied",
//            "PUT",
//            orderEstablished.value
//        )?.let {
//            return true
//        }
//        return false
//    }
}