package com.example.cleaningapp.customer.csCreateOrder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Coupon

class CsCreateOrderViewModel : ViewModel() {
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>() }
    val textDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textTimeBegin: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textTimeEnd: MutableLiveData<String> by lazy { MutableLiveData<String>() }



    val livingroomSize: MutableLiveData<String> by lazy {MutableLiveData<String>()}
}