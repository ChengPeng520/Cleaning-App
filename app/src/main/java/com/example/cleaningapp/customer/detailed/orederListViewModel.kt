package com.example.cleaningapp.customer.detailed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class orederListViewModel: ViewModel(){
    val orderItem: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
}