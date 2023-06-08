package com.example.cleaningapp.customer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.share.requestTask

class OrderdoneViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }

    fun fetchOrdersInfo(orderId: Int) {
        requestTask<Order>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder/$orderId",
            method = "GET",
        )?.let {
            order.value = it
//            Log.d("OrderList", "Fetched orders: $it")
        }
    }
}