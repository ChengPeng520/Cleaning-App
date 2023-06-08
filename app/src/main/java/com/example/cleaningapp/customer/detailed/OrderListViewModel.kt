package com.example.cleaningapp.customer.detailed

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.requestTask

class OrderListViewModel : ViewModel() {
    val orderItem: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val photoUri: MutableLiveData<Uri?> by lazy { MutableLiveData<Uri?>() }

    fun fetchOrdersInfo(orderId: Int) {
        requestTask<Order>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder/$orderId",
            method = "GET",
        )?.let {
            orderItem.value = it
//            Log.d("OrderList", "Fetched orders: $it")
        }
    }
}