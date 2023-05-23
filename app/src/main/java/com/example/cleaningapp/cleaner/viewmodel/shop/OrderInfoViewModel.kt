package com.example.cleaningapp.cleaner.viewmodel.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.OrderInfoItemUiState
import com.example.cleaningapp.cleaner.uistate.OrderInfoUiState

class OrderInfoViewModel : ViewModel() {
    private val _uiState = MutableLiveData<OrderInfoUiState>()
    val uiState: LiveData<OrderInfoUiState> = _uiState

    fun fetchOrderInfo() {
        val orderInfoList = mutableListOf<OrderInfoItemUiState>()
        orderInfoList.add(OrderInfoItemUiState(1, R.drawable.fatruei_test1, "掃把", 100, 1))
        orderInfoList.add(OrderInfoItemUiState(1, R.drawable.fatruei_test1, "掃把", 100, 1))
        orderInfoList.add(OrderInfoItemUiState(1, R.drawable.fatruei_test1, "掃把", 100, 1))
        orderInfoList.add(OrderInfoItemUiState(1, R.drawable.fatruei_test1, "掃把", 100, 1))
        orderInfoList.add(OrderInfoItemUiState(1, R.drawable.fatruei_test1, "掃把", 100, 1))
        orderInfoList.add(OrderInfoItemUiState(1, R.drawable.fatruei_test1, "掃把", 100, 1))
        _uiState.value = OrderInfoUiState(
            date = "2023/4/26",
            state = "已到貨",
            orderInfoItems = orderInfoList,
            grossPrice = 2000
        )
    }
}