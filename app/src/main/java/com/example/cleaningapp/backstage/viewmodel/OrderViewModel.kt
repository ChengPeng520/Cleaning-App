package com.example.cleaningapp.backstage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.model.Order

/**
 * 單一訂單資料處理
 */
class OrderViewModel:ViewModel() {
    val order:MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
}