package com.example.cleaningapp.backstage.shop.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.ShopOrder


class BsShopOrderViewModel : ViewModel() {

  val shopOrder: MutableLiveData<ShopOrder> by lazy { MutableLiveData<ShopOrder>() }
  //單一商品訂單列表畫面

  }
