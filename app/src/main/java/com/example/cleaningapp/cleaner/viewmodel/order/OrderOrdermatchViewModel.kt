package com.example.cleaningapp.cleaner.viewmodel.order

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.CompleteOrderInfoUiState
import com.example.cleaningapp.cleaner.uistate.OrderStateUiState
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.cleaner.uistate.Work
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.OrderUtil
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class OrderOrdermatchViewModel : ViewModel() {
    val order: MutableLiveData<SearchOrder>by lazy { MutableLiveData<SearchOrder>(SearchOrder()) }
    var orderInfo = SearchOrder()
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    var cleanerId: Int = 0
    var orderId:Int = 0
    fun fetchOrdermatch(orderId:Int) {
        Log.d("333","$orderId")
        requestTask<SearchOrder>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/info/$orderId",
            "GET",
            respBodyType = object :TypeToken<SearchOrder>() {}.type
        )?.let {
            order.value = it
            orderInfo = it
            Log.d("444","回傳資料:${orderInfo.orderId}")
            cleanerId = it.cleanerId
            Log.d("TAG","回傳資料:${it.orderId}")
            Log.d("TAG2","回傳資料:${cleanerId}")

        }
    }


    fun DeleteOrder(view: View){
        requestTask<SearchOrder>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/javaweb-cleaningapp/orderApplied/${orderId}/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
            "DELETE",
        )?.let {
            order.value = it
        }
    }
}

