package com.example.cleaningapp.cleaner.viewmodel.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.CheckShopOrder
import com.example.cleaningapp.cleaner.uistate.OrderHistoryItemUiState
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask

class OrderHistoryViewModel : ViewModel() {
    private val _uiState = MutableLiveData<List<OrderHistoryItemUiState>>()
    val uiState: LiveData<List<OrderHistoryItemUiState>> = _uiState

    fun fetchOrderHistory() {
        requestTask<CheckShopOrder>(
            path = "clShopOrder/isChecked/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
            method = "GET",
        )?.let {
            val list: MutableList<OrderHistoryItemUiState> = mutableListOf()
            for (i in 0 until it.shopOrders.size) {
                list.add(
                    OrderHistoryItemUiState(
                        id = it.shopOrders[i].shopOrderId,
                        date = it.shopOrders[i].timeCreate,
                        totalCount = it.cList[i].size,
                        image = it.cList[i][0].photo,
                        name = it.cList[i][0].name,
                        unitPrice = it.cList[i][0].price,
                        number = it.cList[i][0].count,
                        grossPrice = it.shopOrders[i].totalPrice,
                        isDelivered = it.shopOrders[i].isDelivered,
                        isShipped = it.shopOrders[i].isShipped
                    )
                )
            }
            _uiState.value = list
        }
    }
}