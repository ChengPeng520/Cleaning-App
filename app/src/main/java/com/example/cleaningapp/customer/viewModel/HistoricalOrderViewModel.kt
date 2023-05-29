package com.example.cleaningapp.customer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order

class HistoricalOrderViewModel : ViewModel() {
    val orderList: MutableLiveData<List<Order>> by lazy { MutableLiveData<List<Order>>() }

    fun fetchOrders() {
        val list = mutableListOf<Order>()
        list.add(Order(1, 1001, "2023-05-11", "10:00", "台北市信義區", "100坪", "什麼都有，人來就好", "John Doe", 50))
        list.add(Order(2, 1002, "2023-05-12", "14:30", "新北市蘆洲區", "40坪", "路上注意安全", "Jane Smith", 75))
        list.add(Order(3, 1003, "2023-05-13", "09:00", "新北市三重區", "50坪", "可以順便幫我買飲料嗎", "Mike Johnson", 35))
        list.add(Order(1, 1004, "2023-05-14", "12:00", "新北市泰山區", "30坪", "來包菸吧", "Sarah Davis", 60))
        list.add(Order(2, 1005, "2023-05-15", "15:30", "台北市天母區", "200坪", "應有盡有", "Chris Wilson", 80))
        orderList.value = list
    }
}