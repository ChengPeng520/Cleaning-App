package com.example.cleaningapp.customer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order

class HistoricalOrderViewModel : ViewModel() {
    val orderList: MutableLiveData<List<Order>> by lazy { MutableLiveData<List<Order>>() }

    fun fetchOrders(){
        val list = mutableListOf<Order>()
        list.add(Order(1, 1001, "2023-05-11", "10:00", "123 Street", "Small", "No special requests", "John Doe", 50))
        list.add(Order(2, 1002, "2023-05-12", "14:30", "456 Avenue", "Medium", "Need extra cleaning", "Jane Smith", 75))
        list.add(Order(3, 1003, "2023-05-13", "09:00", "789 Road", "Large", "Pet-friendly cleaning", "Mike Johnson", 35))
        list.add(Order(1, 1004, "2023-05-14", "12:00", "321 Lane", "Small", "No special requests", "Sarah Davis", 60))
        list.add(Order(2, 1005, "2023-05-15", "15:30", "654 Court", "Medium", "Need deep cleaning", "Chris Wilson", 80))
        orderList.value = list
    }
}