package com.example.cleaningapp.customer.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask

class OrderingViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }

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