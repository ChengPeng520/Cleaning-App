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
    // 受監控的LiveData，一旦指派新值就會更新Cleaner列表畫面
    val order: MutableLiveData<List<Work>> by lazy { MutableLiveData<List<Work>>() }

    fun onTabSelected(tabNumber: Int) {
        _selectedTab.value = tabNumber
    }

    init {
        loadOrders()
    }

    /** 模擬取得遠端資料 */
    fun loadOrders() {
        val orderList = mutableListOf<Work>()
        orderList.add(Work(1,R.drawable.cs_img_xxx,"11111111","2023年4月26日","12:00-14:00",
            "臺北市中山區南京東路三段219號5樓",5.0,5.0,5.0,5.0,
        "台北市中山區","工具:  吸塵器 、拖把\n" +
                    "整理重點:  臥房、客房 ", "1111元整"))

        orderList.add(Work(1,R.drawable.alb_account_avatar, "22222222", "2023年4月27日","13:00-15:00",
            "臺北市中山區南京東路三段219號6樓",5.0,5.0,5.0,5.0,
        "台北市中山區","工具:  吸塵器 、拖把\n" +
                    "整理重點:  臥房、客房 ","2222元整"))

        orderList.add(Work(2,R.drawable.alb_account_avatar, "33333333", "2023年4月28日","14:00-16:00",
            "臺北市中山區南京東路三段219號7樓",5.0,5.0,5.0,5.0,
            "台北市中山區","工具:  吸塵器 、拖把\n" +
                    "整理重點:  臥房、客房 ","3333元整"))

        this.orderList = orderList
        this.order.value = this.orderList
    }
}

