package com.example.cleaningapp.backstage.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.shopOrder
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class BsShopOrdersViewModel:ViewModel(){
    private var shopOrderList = mutableListOf<shopOrder>()
    val shopOrders: MutableLiveData<List<shopOrder>> by lazy { MutableLiveData<List<shopOrder>>() }


        init {
            loadOrderList()
        }

        fun loadOrderList() {
            requestTask<List<shopOrder>>(
                "http://10.0.2.2:8080/javaweb-cleaningapp/ShopOrder/selectAll/",
            "GET" ,
                    respBodyType = object :TypeToken<List<shopOrder>>() {} .type
            )?.let {
                shopOrders.value =it

            }
        }

        fun search(netText: String) {
            if (netText.isEmpty()) {
                shopOrders.value = shopOrderList
            } else {
                val searchOfOrder = mutableListOf<shopOrder>()
                shopOrderList.forEach { shopOrder ->
                    if (shopOrder.recieverName?.contains(netText,true) == true){
                        searchOfOrder.add(shopOrder)
                    }
                }
                shopOrders.value =searchOfOrder
            }
        }

    }
