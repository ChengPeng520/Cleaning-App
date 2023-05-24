package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.Work

class OrderConductViewModel : ViewModel() {
    private var orderList = mutableListOf<Work>()
    private val _selectedTab = MutableLiveData<Int>()
    val selectedTab: LiveData<Int> get() = _selectedTab

    fun onTabSelected(tabNumber: Int) {
        _selectedTab.value = tabNumber
    }

    // 受監控的LiveData，一旦指派新值就會更新Cleaner列表畫面
    val order: MutableLiveData<List<Work>> by lazy { MutableLiveData<List<Work>>() }

    init {
        loadOrders()
    }

    /** 模擬取得遠端資料 */
    private fun loadOrders() {
        val orderList = mutableListOf<Work>()
        orderList.add(Work(R.drawable.cs_img_xxx, "12:00-14:00(2小時)", "台北"))
        orderList.add(Work(R.drawable.alb_account_avatar, "12:00-14:00(2小時)", "台南"))
        orderList.add(Work(R.drawable.alb_account_avatar, "12:00-14:00(2小時)", "台中"))
        this.orderList = orderList
        this.order.value = this.orderList
    }
}

