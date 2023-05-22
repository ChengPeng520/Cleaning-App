package com.example.cleaningapp.backstage.shop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.shopOrder

class BsShopOrdersViewModel:ViewModel(){

         val shopOrders: MutableLiveData<List<shopOrder>> by lazy { MutableLiveData<List<shopOrder>>() }
        private var shopOrderList = mutableListOf<shopOrder>()

        init {
            loadOrderList()
        }

        private fun loadOrderList() {
            //取得遠端資料

            val shopOrderList = mutableListOf<shopOrder>()
            shopOrderList.add(
                shopOrder(
                    "12345678",
                    "處理中",
                    "2023-05-17",
                    "5",
                    "5000",
                    "Albert",
                    "0912345789",
                    "台北市南京復興區嘿嘿路嘿嘿巷12號3樓"

                )
            )
            shopOrderList.add(
                shopOrder(
                    "12345678",
                    "執行中",
                    "2023-05-17",
                    "5",
                    "5000",
                    "Albert",
                    "0912345789",
                    "台北市南京復興區嘿嘿路嘿嘿巷12號3樓"

                )
            )
            this.shopOrderList = shopOrderList
            this.shopOrders.value = this.shopOrderList

        }

        fun search(netText: String) {
            if (netText.isEmpty() || netText == null) {
                shopOrders.value = shopOrderList
            } else {
                val searchOfOrder = mutableListOf<shopOrder>()
                ///前面一開始宣告的訂單.forEach 所代表每一個訂單列表中的一筆訂單,使用lambda表示式去核對netText所輸入的值與
                // .num或.name的字串是否與
                shopOrderList.forEach { shopOrder ->
                    if (shopOrder.num.contains(netText,true)){
                        searchOfOrder.add(shopOrder)
                    }
                }
                shopOrders.value =searchOfOrder
            }
        }

    }
