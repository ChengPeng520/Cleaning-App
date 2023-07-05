package com.example.cleaningapp.backstage.shop.viewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.OrderDetail
import com.example.cleaningapp.backstage.shop.Product
import com.example.cleaningapp.backstage.shop.ShopOrder
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

//訂單細項資料列表
class BsShopOrdersDetailViewModel : ViewModel() {
    val ordersDetail: MutableLiveData<List<OrderDetail>> by lazy { MutableLiveData<List<OrderDetail>>() }
    val shopOrder: MutableLiveData<ShopOrder> by lazy { MutableLiveData<ShopOrder>(ShopOrder()) }
    val productId: MutableLiveData<Product> by lazy { MutableLiveData<Product>() }
    val orderDetail: MutableLiveData<OrderDetail> by lazy { MutableLiveData<OrderDetail>() }
    val order: MutableLiveData<ShopOrder> by lazy { MutableLiveData<ShopOrder>() }

    fun loadShopOrderList(shopOrderId: Int?) {
        requestTask<ShopOrder>(
            "${Constants.BASE_URL}/ShopOrder/info/$shopOrderId",
            "GET",
        )?.let {
            shopOrder.value = it
        }
    }

    fun shopOrderModify(): Boolean {
        requestTask<ShopOrder>(
            "${Constants.BASE_URL}/ShopOrder/",
            "PUT",
            shopOrder.value
        )?.let {
            return true
            Log.d("TAG", "shopOrder修改成功:$it")
        }
        return false
    }

    fun loadOrdersDetailList(shopOrderId: Int) {
        requestTask<List<OrderDetail>>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clShopOrder/shopInfo/$shopOrderId",
            "GET",
            respBodyType = object : TypeToken<List<OrderDetail>>() {}.type
        )?.let {
            ordersDetail.value = it
            Log.d("TAG", "回傳內容$it")
        }
    }
}