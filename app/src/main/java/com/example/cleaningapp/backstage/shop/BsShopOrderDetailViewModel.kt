package com.example.cleaningapp.backstage.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BsShopOrderDetailViewModel : ViewModel() {

    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
}