package com.example.cleaningapp.cleaner.viewmodel.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.OrderHistoryItemUiState
import com.example.cleaningapp.cleaner.uistate.OrderHistoryUiState

class OrderHistoryViewModel : ViewModel() {
    private val _uiState = MutableLiveData<OrderHistoryUiState>()
    val uiState: LiveData<OrderHistoryUiState> = _uiState

    fun fetchOrderHistory() {
        val orderHistoryList = mutableListOf<OrderHistoryItemUiState>()
        orderHistoryList.add(
            OrderHistoryItemUiState(
                1,
                "2023/4/26",
                2,
                R.drawable.fatruei_test1,
                "掃把",
                100,
                1,
                200
            )
        )
        orderHistoryList.add(
            OrderHistoryItemUiState(
                2,
                "2023/4/26",
                1,
                R.drawable.fatruei_test3,
                "拖把組",
                100,
                1,
                100
            )
        )
        _uiState.value = OrderHistoryUiState(orderHistoryItems = orderHistoryList)
    }
}