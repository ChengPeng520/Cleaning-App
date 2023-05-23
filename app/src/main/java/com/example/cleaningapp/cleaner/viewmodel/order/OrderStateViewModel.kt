package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.OrderStateUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class OrderStateViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OrderStateUiState())
    val uiState: StateFlow<OrderStateUiState> = _uiState

    fun fetchOrderProgress() {
        _uiState.value = OrderStateUiState(
            orderId = 10001,
            dateOrdered = "2023年4月26日",
            orderedTime = "12:00-14:00(2小時)",
            address = "台北市中山區南京東路三段219號5樓",
            livingRoomSize = 5,
            remark = "工具:吸塵器、拖把\n整理重點:臥房、客房",
            priceForCleaner = 1000,
            status = 1
        )
    }

    fun startCleaning() {
        _uiState.update {
            it.copy(status = 2)
        }
    }

    fun next() {
        _uiState.update {
            it.copy(status = 3)
        }
    }

    fun next4() {
        _uiState.update {
            it.copy(status = 4)
        }
    }
}