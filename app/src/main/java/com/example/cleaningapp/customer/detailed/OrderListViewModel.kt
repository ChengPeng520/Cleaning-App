package com.example.cleaningapp.customer.detailed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask

class OrderListViewModel : ViewModel() {
    val orderItem: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }

    fun fetchOrdersInfo(orderId: Int) {
        requestTask<Order>(
            path = "csOrder/$orderId",
            method = "GET",
        )?.let {
            orderItem.value = it
        }
    }
}