package com.example.cleaningapp.backstage.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R

class OrderListViewModel : ViewModel() {
    //原始訂單列表
    private var orderList = mutableListOf<Order>()

    //受監控的LiveDate 一旦指派新值就會更新訂單畫面
    val orders: MutableLiveData<List<Order>> by lazy { MutableLiveData<List<Order>>() }

    init {
        loadOrders()
    }

    /** 模擬取得遠端資料 */
    private fun loadOrders() {
        val orderList = mutableListOf<Order>()
        orderList.add(
            Order(
                "123456789",
                "執行中",
                "2023-03-08",
                "12:00-14:00",
                2,
                "台北市南京復興區123路2巷12號3樓",
                "房間5坪",
                "吸塵器 、拖把",
                "臥房、客房",
                "5000",
                "30",
                "-30",
                "5000",
                R.drawable.before1,
                R.drawable.before2,
                R.drawable.before3,
                R.drawable.after1,
                R.drawable.after2,
                R.drawable.after3
            )
        )

        this.orderList = orderList
        this.orders.value = this.orderList
    }


    /**
     * 如果搜尋為空字串,就顯示原始訂單列表;否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String) {
        //如果搜尋框輸入為空值或空白,那顯示的值全部列表資料
        if (newText.isEmpty()) {
            orders.value = orderList
        } else {
            //mutableListOf 表示該集合是可變的，可以動態地添加、刪除和修改元,<order>為一個泛型參數
            val searchOrderList = mutableListOf<Order>()
            //order是指前面的orderList.forEach 所代表每一個訂單列表中的一筆訂單,使用lambda表示式
            orderList.forEach { order ->
                if (order.Number.contains(newText, true)) {
                    searchOrderList.add(order)
                }
            }
            orders.value = searchOrderList


        }

    }


}