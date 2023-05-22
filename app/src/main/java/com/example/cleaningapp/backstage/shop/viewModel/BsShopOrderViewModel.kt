package com.example.cleaningapp.backstage.shop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.shopOrder

class BsShopOrderViewModel : ViewModel() {
  val shopOrder : MutableLiveData<shopOrder> by lazy { MutableLiveData<shopOrder>() }
    //單一商品訂單列表畫面
}