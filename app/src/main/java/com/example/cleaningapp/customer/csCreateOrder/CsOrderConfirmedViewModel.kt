package com.example.cleaningapp.customer.csCreateOrder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.CreateOrderPhoto
import com.example.cleaningapp.customer.model.Order

class CsOrderConfirmedViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val photo: MutableLiveData<CreateOrderPhoto> by lazy { MutableLiveData<CreateOrderPhoto>() }

}