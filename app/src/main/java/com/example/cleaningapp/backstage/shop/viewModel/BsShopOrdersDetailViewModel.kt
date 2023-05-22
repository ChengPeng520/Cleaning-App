package com.example.cleaningapp.backstage.shop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.OrderDetail
//訂單細項資料列表
class BsShopOrdersDetailViewModel : ViewModel() {
    val ordersdetail: MutableLiveData<List<OrderDetail>> by lazy { MutableLiveData<List<OrderDetail>>() }
    private var ordersDetailList = mutableListOf<OrderDetail>()

    //初始化資料,用init可以先執行初始化實例

    init {
        loadOrdersDetailList()

    }

    fun loadOrdersDetailList() {
        //要把假資料放入orderDetailList
        val orderDetailList = mutableListOf<OrderDetail>()
        orderDetailList.add(
            (OrderDetail(
                R.drawable.product1,
                "掃把",
                "200元",
                "X5"

            ))
        )
        orderDetailList.add(
            (OrderDetail(R.drawable.product2,
                "拖把",
                "300元",
                "X5"
             ))
        )
        this.ordersDetailList = orderDetailList
        this.ordersdetail.value = ordersDetailList
    }

}
