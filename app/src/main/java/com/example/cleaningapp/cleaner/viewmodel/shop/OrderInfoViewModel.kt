package com.example.cleaningapp.cleaner.viewmodel.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.OrderHistoryItemUiState
import com.example.cleaningapp.cleaner.uistate.OrderInfoItemUiState
import com.example.cleaningapp.cleaner.uistate.OrderInfoUiState
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class OrderInfoViewModel : ViewModel() {
    var orderHistoryItem: OrderHistoryItemUiState = OrderHistoryItemUiState()
    private val _uiState = MutableLiveData<OrderInfoUiState>()
    val uiState: LiveData<OrderInfoUiState> = _uiState

    fun fetchOrderInfo(shopOrderId: Int?) {
        shopOrderId?.let {
            requestTask<List<OrderInfoItemUiState>>(
                path = "clShopOrder/shopInfo/${orderHistoryItem.id}",
                method = "GET",
                respBodyType = object : TypeToken<List<OrderInfoItemUiState>>() {}.type
            )?.let {
                _uiState.value = OrderInfoUiState(
                    date = orderHistoryItem.date!!,
                    orderInfoItems = it,
                    grossPrice = orderHistoryItem.grossPrice,
                    isDelivered = orderHistoryItem.isDelivered,
                    isShipped = orderHistoryItem.isShipped
                )
            }
        }
    }
}