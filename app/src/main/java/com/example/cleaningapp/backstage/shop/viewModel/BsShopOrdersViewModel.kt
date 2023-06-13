package com.example.cleaningapp.backstage.shop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.ShopOrder
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class BsShopOrdersViewModel:ViewModel(){
    var shopOrderList = listOf<ShopOrder>()
    val shopOrders: MutableLiveData<List<ShopOrder>> by lazy { MutableLiveData<List<ShopOrder>>() }


        init {
            loadOrderList()
        }

        fun loadOrderList() {
            requestTask<List<ShopOrder>>(
                "${Constants.BASE_URL}/ShopOrder/selectAll/",
            "GET" ,
                    respBodyType = object :TypeToken<List<ShopOrder>>() {} .type
            )?.let {
                shopOrders.value =it
                shopOrderList = it
            }
        }

        fun search(netText: String) {
            if (netText.isEmpty()) {
                shopOrders.value = shopOrderList
            } else {
                val searchOfOrder = mutableListOf<ShopOrder>()
                shopOrderList.forEach { shopOrder ->
                    if (shopOrder.recieverName?.contains(netText,true) == true){
                        searchOfOrder.add(shopOrder)
                    }
                }
                shopOrders.value =searchOfOrder
            }
        }

    }
