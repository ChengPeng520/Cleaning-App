package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.*
import com.example.cleaningapp.cleaner.uistate.OrderChatroomItemUiState
import com.example.cleaningapp.cleaner.uistate.OrderInfo
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class OrderChatroomViewModel : ViewModel() {
    private val _orderUiState: MutableLiveData<OrderInfo> by lazy { MutableLiveData<OrderInfo>() }
    val orderUiState: LiveData<OrderInfo> by lazy { _orderUiState }
    private val _chatroomUiState: MutableLiveData<List<OrderChatroomItemUiState>> by lazy { MutableLiveData<List<OrderChatroomItemUiState>>() }
    val chatroomUiState: LiveData<List<OrderChatroomItemUiState>> by lazy { _chatroomUiState }
    val commitText = MutableLiveData("")

    fun fetchOrderChatRoomTalkList() {
        requestTask<List<OrderChatroomItemUiState>>(
            url = "${Constants.BASE_URL}/ChatCustCln/${orderUiState.value?.customerId}/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
            method = "GET",
            respBodyType = object : TypeToken<List<OrderChatroomItemUiState>>() {}.type
        )?.let {
            _chatroomUiState.value = it
        }
    }

    fun setOrderUiState(order: OrderInfo) {
        _orderUiState.value = order
    }

    fun commitText() {
        if (commitText.value.toString().isNotEmpty()) {
            requestTask<JsonObject>(
                url = "${Constants.BASE_URL}/ChatCustCln/",
                method = "POST",
                reqBody = OrderChatroomItemUiState(
                    cleanerId = CleanerSharedPreferencesUtils.getCurrentCleanerId(),
                    text = commitText.value!!
                )
            )?.let {
                if (it.get("result").toString().toBoolean()) {
                    fetchOrderChatRoomTalkList()
                    commitText.value = ""
                }
            }
        }
    }
}