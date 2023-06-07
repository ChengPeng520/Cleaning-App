package com.example.cleaningapp.customer.detailed

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class OrderListViewModel : ViewModel() {
    val orderItem: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val photoUri: MutableLiveData<Uri?> by lazy { MutableLiveData<Uri?>() }

    init {
        fetchOrdersInfo()
    }


    private fun fetchOrdersInfo() {
        requestTask<Order>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder/1/",
            method = "GET",
        )?.let {
            orderItem.value = it
            Log.d("OrderList", "Fetched orders: $it")
        }
    }
}