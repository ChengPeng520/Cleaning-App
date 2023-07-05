package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class OrderConductViewModel : ViewModel() {
    // 受監控的LiveData，一旦指派新值就會更新Cleaner列表畫面
    var orderList = listOf<SearchOrder>()
    val order: MutableLiveData<List<SearchOrder>> by lazy { MutableLiveData<List<SearchOrder>>() }

    init {
        fetchOrderRecord()
    }

    fun fetchOrderRecord() {
        requestTask<List<SearchOrder>>(
            "clnOrder/orderRecord/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}/",
            "GET",
            respBodyType = object : TypeToken<List<SearchOrder>>() {}.type
        )?.let { searchOrders ->
            orderList = searchOrders
            order.value = orderList.filter { it.status == 0 }
        }
    }
}