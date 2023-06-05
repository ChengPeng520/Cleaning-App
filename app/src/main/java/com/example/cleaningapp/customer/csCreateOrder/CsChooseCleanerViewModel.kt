package com.example.cleaningapp.customer.csCreateOrder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.Comment
import com.example.cleaningapp.customer.model.CouponObtain
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class CsChooseCleanerViewModel : ViewModel() {
    //受監控的LiveDATA，一旦指派新值就會更新使用者列表畫面
    val cleanerList: MutableLiveData<List<Cleaner>> by lazy { MutableLiveData<List<Cleaner>>() }
    val commentItem: MutableLiveData<Comment> by lazy { MutableLiveData<Comment>() }
    //  原始使用者列表
    private var cleaners = mutableListOf<Cleaner>()

    init {
        fetchCleanerApplied()
    }

    fun fetchCleanerApplied() {
        requestTask<List<Cleaner>>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/choose/1",
            respBodyType = object : TypeToken<List<Cleaner>>() {}.type
        )?.let {
            cleanerList.value = it
        }
    }
}