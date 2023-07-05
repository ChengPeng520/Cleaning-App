package com.example.cleaningapp.backstage.order

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class OrderListViewModel : ViewModel() {
    //原始訂單列表
    private var orderList = listOf<Order>()

    //受監控的LiveDate 一旦指派新值就會更新訂單畫面
    val orders: MutableLiveData<List<Order>> by lazy { MutableLiveData<List<Order>>() }

    init {
        loadOrders()
    }

    /** 模擬取得遠端資料 */
    private fun loadOrders() {
        requestTask<List<Order>>(
            "bsOrder/",
            "GET",
            respBodyType = object : TypeToken<List<Order>>() {}.type
        )?.let {
            orders.value = it
            orderList = it
            Log.d("TAG", "接收內容:${it}}")
        }
    }

    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            orders.value = orderList
        } else {
            val searchOrderList = mutableListOf<Order>()
            orderList.forEach { order ->
                if (order.searchOrderId.contains(newText, true))
                    searchOrderList.add(order)
            }
            orders.value = searchOrderList
        }
    }
}