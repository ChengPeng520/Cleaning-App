package com.example.cleaningapp.customer.csHomePage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.CouponObtain
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class CsHomePageViewModel : ViewModel() {
    val cleaners : MutableLiveData<List<Cleaner>> by lazy { MutableLiveData<List<Cleaner>>() }
    val cleaner: MutableLiveData<Cleaner> by lazy { MutableLiveData<Cleaner>() }

    init {
        fetchCleaners()
    }

    fun fetchCleaners() {
        requestTask<List<Cleaner>>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied/choose/*",
            respBodyType = object : TypeToken<List<Cleaner>>() {}.type
        )?.let {
            cleaners.value = it
        }
    }
}