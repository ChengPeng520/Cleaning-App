package com.example.cleaningapp.backstage.coupon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BackstageCouponModifyViewModel:ViewModel() {

    val name :MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val percentage:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val discount:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val quantity:MutableLiveData<String>by lazy { MutableLiveData<String>() }
    val limit:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val minimum:MutableLiveData<String> by lazy { MutableLiveData<String>() }



}