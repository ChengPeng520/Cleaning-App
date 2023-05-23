package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.CompleteOrderInfoUiState

class CompleteOrderInfoViewModel : ViewModel() {
    private val _uiState: MutableLiveData<CompleteOrderInfoUiState> by lazy { MutableLiveData<CompleteOrderInfoUiState>() }
    val uiState: LiveData<CompleteOrderInfoUiState> by lazy { _uiState }

    fun fetchOrderInfo() {
        _uiState.value = CompleteOrderInfoUiState(
            orderId = 10001,
            dateOrdered = "2023年4月26日",
            orderedTime = "12:00-14:00(2小時)",
            address = "台北市中山區南京東路三段219號5樓",
            livingRoomSize = 5,
            remark = "工具:吸塵器、拖把\n整理重點:臥房、客房",
            priceForCleaner = 1000,
            stars = 4.0f,
            commentCleaner = "提早到,整理得很細心"
        )
    }
}