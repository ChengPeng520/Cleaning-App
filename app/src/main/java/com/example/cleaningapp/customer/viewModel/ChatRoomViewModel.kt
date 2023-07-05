package com.example.cleaningapp.customer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.chatroom.ChatroomItemUiState
import com.example.cleaningapp.customer.chatroom.ChatroomUiState
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken


class ChatRoomViewModel : ViewModel() {
    private val _uiState = MutableLiveData<ChatroomUiState>()
    val uiState: LiveData<ChatroomUiState> = _uiState
    val commitText = MutableLiveData("")

    init {
        fetchChatRoomTalkList()
    }

    fun fetchChatRoomTalkList() {
        requestTask<List<ChatroomItemUiState>>(
            path = "ChatCustBack/${CustomerSharePreferencesUtils.getCurrentCustomerId()}",
            method = "GET",
            respBodyType = object : TypeToken<List<ChatroomItemUiState>>() {}.type
        )?.let {
            _uiState.value = ChatroomUiState(it)
        }
    }

    fun commitText() {
        if (commitText.value.toString().isNotEmpty()) {
            requestTask<JsonObject>(
                path = "ChatCustBack",
                method = "POST",
                reqBody = ChatroomItemUiState(
                    customerId = CustomerSharePreferencesUtils.getCurrentCustomerId(),
                    text = commitText.value!!
                )
            )?.let {
                if (it.get("result").toString().toBoolean()) {
                    fetchChatRoomTalkList()
                    commitText.value = ""
                }
            }
        }
    }
}