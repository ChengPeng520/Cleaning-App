package com.example.cleaningapp.backstage.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.order.Order

class OrderListViewModel:ViewModel() {
    //原始訂單列表
    private  var orderList = mutableListOf<Order>()
    //受監控的LiveDate 一旦指派新值就會更新訂單畫面
    val orders:MutableLiveData<List<Order>> by lazy { MutableLiveData<List<Order>>() }

    init {
        loadOrders()
    }

    private fun loadOrders() {
        TODO("Not yet implemented")
    }

    /**
     * 如果搜尋為空字串,就顯示原始訂單列表;否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
     fun seacher(newText:String){

     }
}