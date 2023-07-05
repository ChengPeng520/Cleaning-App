package com.example.cleaningapp.customer.csHomePage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.OrderRemind
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class CsHomePageViewModel : ViewModel() {
    val order: MutableLiveData<OrderRemind> by lazy { MutableLiveData<OrderRemind>(OrderRemind()) }
    val cleaners: MutableLiveData<List<Cleaner>> by lazy { MutableLiveData<List<Cleaner>>() }
    val cleaner: MutableLiveData<Cleaner> by lazy { MutableLiveData<Cleaner>() }

    init {
        fetchCleaners()
        getOrder()
    }

    private fun fetchCleaners() {
        requestTask<List<Cleaner>>(
            "orderApplied/best",
            respBodyType = object : TypeToken<List<Cleaner>>() {}.type
        )?.let {
            cleaners.value = it
        }
    }

    private fun getOrder() {
        requestTask<OrderRemind>(
            "csOrder/remind/${CustomerSharePreferencesUtils.getCurrentCustomerId()}/1"
        )?.let {
            order.value = it
        }
    }
}