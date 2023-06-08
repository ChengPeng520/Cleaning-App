package com.example.cleaningapp.backstage.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.OrderDetail
import com.example.cleaningapp.backstage.shop.shopOrder
import com.example.cleaningapp.share.requestTask

//訂單細項資料列表
class BsShopOrdersDetailViewModel : ViewModel() {
    val ordersdetail: MutableLiveData<List<OrderDetail>> by lazy { MutableLiveData<List<OrderDetail>>() }
    private var ordersDetailList = mutableListOf<OrderDetail>()
    val shopOrder: MutableLiveData<shopOrder> by lazy { MutableLiveData<shopOrder>() }
    //單一商品訂單列表畫面


    init {
//        loadOrdersDetailList()
    }

    fun loadShopOrderDetail(shopOrderId: Int?) {
        requestTask<shopOrder>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/ShopOrder/info/$shopOrderId",
            "GET",
        )?.let {
            shopOrder.value = it
            Log.d("TAG", "接收內容:$shopOrderId")
        }
    }
        fun shopOrderModify(): Boolean {
            requestTask<shopOrder>(
                "http://10.0.2.2:8080/javaweb-cleaningapp/ShopOrder/",
                "PUT",
                shopOrder.value
            )?.let {
                return true
            }
            return false
        }


            fun loadOrdersDetailList() {


            }

        }


