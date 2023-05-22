package com.example.cleaningapp.backstage.shop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.OrderDetail
import com.example.cleaningapp.backstage.shop.shopOrder

class BsShopOrderDetailViewModel : ViewModel() {

     val ordersDetail: MutableLiveData<OrderDetail> by lazy { MutableLiveData<OrderDetail>() }

}
//一筆訂單細項