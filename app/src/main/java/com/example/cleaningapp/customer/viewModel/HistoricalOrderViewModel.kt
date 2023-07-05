package com.example.cleaningapp.customer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class HistoricalOrderViewModel : ViewModel() {
    val orderList: MutableLiveData<List<Order>> by lazy { MutableLiveData<List<Order>>() }

    init {
        fetchOrdersInfo()
    }

//   fun fetchOrders() {
//        val list = mutableListOf<Order>()
//        list.add(Order(0, 1001, "2023-05-11", "10:00", "台北市信義區", "100坪", "什麼都有，人來就好", "John Doe", 50))
//        list.add(Order(1, 1002, "2023-05-12", "14:30", "新北市蘆洲區", "40坪", "路上注意安全", "Jane Smith", 75))
//        list.add(Order(2, 1003, "2023-05-13", "09:00", "新北市三重區", "50坪", "可以順便幫我買飲料嗎", "Mike Johnson", 35))
//        list.add(Order(3, 1004, "2023-05-14", "12:00", "新北市泰山區", "30坪", "來包菸吧", "Sarah Davis", 60))
//        list.add(Order(4, 1005, "2023-05-15", "15:30", "台北市天母區", "200坪", "應有盡有", "Chris Wilson", 80))
//        list.add(Order(5, 1006, "2023-05-16", "15:32", "台北市天母區", "32坪", "您好", "Chris Wilson", 80))
//        list.add(Order(6, 1007, "2023-05-17", "15:33", "台北市天母區", "33坪", "辛苦了", "Chris Wilson", 80))
//        list.add(Order(7, 1008, "2023-05-18", "15:34", "台北市天母區", "23坪", "再麻煩了", "Chris Wilson", 80))
//        list.add(Order(8, 1009, "2023-05-19", "15:35", "台北市天母區", "54坪", "感恩有您", "Chris Wilson", 80))
//        list.add(Order(0, 1010, "2023-05-20", "15:36", "台北市天母區", "70坪", "3Q", "Chris Wilson", 80))
//        orderList.value = list
//   }

    fun fetchOrdersInfo() {
        requestTask<List<Order>>(
            path = "csOrder/1/${CustomerSharePreferencesUtils.getCurrentCustomerId()}",
            method = "GET",
            respBodyType = object : TypeToken<List<Order>>() {}.type
        )?.let {
            orderList.value = it
//            Log.d("OrderList", "Fetched orders: $it")
        }
    }
}