package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.CompleteOrderInfoUiState
import com.example.cleaningapp.cleaner.uistate.InsertOrder
import com.example.cleaningapp.share.requestTask

class CompleteOrderInfoViewModel : ViewModel() {
    private val _uiState: MutableLiveData<CompleteOrderInfoUiState> by lazy { MutableLiveData<CompleteOrderInfoUiState>() }
    val uiState: LiveData<CompleteOrderInfoUiState> by lazy { _uiState }

    fun fetchOrderInfo() {
        requestTask<InsertOrder>(
            "http://192.168.18.26:8080/javaweb-cleaningapp/clnOrder/info/1",
            "GET"
        )?.let {
            _uiState.value = it.order
        }
    }
}