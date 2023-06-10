package com.example.cleaningapp.backstage.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.OrderDetail
import com.example.cleaningapp.backstage.shop.ShopOrder
import com.example.cleaningapp.share.requestTask

class BsShopOrderDetailViewModel : ViewModel() {
     val orderDetailList: MutableLiveData<List<OrderDetail>> by lazy { MutableLiveData() }
     val ordersDetail: MutableLiveData<OrderDetail> by lazy { MutableLiveData<OrderDetail>() }
     val order:MutableLiveData<ShopOrder>  by lazy { MutableLiveData<ShopOrder>() }
     }


//一筆訂單細項