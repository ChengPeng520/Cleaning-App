package com.example.cleaningapp.backstage.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.shopOrder
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class BsShopOrderViewModel : ViewModel() {

  val shopOrder: MutableLiveData<shopOrder> by lazy { MutableLiveData<shopOrder>() }
  //單一商品訂單列表畫面

  }
