package com.example.cleaningapp.customer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask

class OrderChatroomViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val orderId: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    fun fetchOrdersInfo(orderId: Int) {
        requestTask<Order>(
            url = "${Constants.BASE_URL}/csOrder/$orderId",
            method = "GET",
        )?.let {
            order.value = it
//            Log.d("OrderList", "Fetched orders: $it")
        }
    }
}