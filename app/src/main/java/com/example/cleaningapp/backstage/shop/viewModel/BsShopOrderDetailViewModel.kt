package com.example.cleaningapp.backstage.shop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.Order

class BsShopOrderDetailViewModel : ViewModel() {

    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
}