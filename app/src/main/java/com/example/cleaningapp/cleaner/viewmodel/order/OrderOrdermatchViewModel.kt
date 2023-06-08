package com.example.cleaningapp.cleaner.viewmodel.order

import android.util.Log
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
    val order: MutableLiveData<SearchOrder>by lazy { MutableLiveData<SearchOrder>() }
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun fetchOrdermatch(orderId:Int) {
        requestTask<SearchOrder>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/info/$orderId",
            "GET",
        )?.let {
            order.value = it
        }
    }

//    fun DeleteOrder(){
//        requestTask<SearchOrder>(
//            "http://10.0.2.2:8080/javaweb-cleaningapp/javaweb-cleaningapp/orderApplied/",
//            "DELETE",
//        )?.let {
//            order.value = it
//        }
//    }
}

