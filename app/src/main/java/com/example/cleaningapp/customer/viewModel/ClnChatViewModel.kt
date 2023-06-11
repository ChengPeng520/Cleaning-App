package com.example.cleaningapp.customer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.chatroom.ClnChatMessage
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class ClnChatViewModel : ViewModel() {
    private val _chatroomUiState: MutableLiveData<List<ClnChatMessage>> by lazy { MutableLiveData<List<ClnChatMessage>>() }
    val chatroomUiState: LiveData<List<ClnChatMessage>> by lazy { _chatroomUiState }
    var cleanerId = 0
    val commitText = MutableLiveData("")

    fun fetchOrderChatRoomTalkList() {
        requestTask<List<ClnChatMessage>>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/ChatCustCln/${CustomerSharePreferencesUtils.getCurrentCustomerId()}/$cleanerId",
            method = "GET",
            respBodyType = object : TypeToken<List<ClnChatMessage>>() {}.type
        )?.let {
            _chatroomUiState.value = it
        }
    }

    fun commitText() {
        if (commitText.value.toString().isNotEmpty()) {
            requestTask<JsonObject>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/ChatCustCln/",
                method = "POST",
                reqBody = ClnChatMessage(
                    customerId = CustomerSharePreferencesUtils.getCurrentCustomerId(),
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