package com.example.cleaningapp.cleaner.viewmodel.member


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.ChatroomItemUiState
import com.example.cleaningapp.cleaner.uistate.ChatroomUiState
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class CleanerChatroomViewModel : ViewModel() {
    private val _uiState = MutableLiveData<ChatroomUiState>()
    val uiState: LiveData<ChatroomUiState> = _uiState
    val commitText = MutableLiveData("")

    init {
        fetchChatRoomTalkList()
    }

    fun fetchChatRoomTalkList() {
        requestTask<List<ChatroomItemUiState>>(
            url = "${Constants.BASE_URL}/ChatClnBack/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
            method = "GET",
            respBodyType = object : TypeToken<List<ChatroomItemUiState>>() {}.type
        )?.let {
            _uiState.value = ChatroomUiState(it)
        }
    }

    fun commitText() {
        if (commitText.value.toString().isNotEmpty()) {
            requestTask<JsonObject>(
                url = "${Constants.BASE_URL}/ChatClnBack",
                method = "POST",
                reqBody = ChatroomItemUiState(
                    cleanerId = CleanerSharedPreferencesUtils.getCurrentCleanerId(),
                    text = commitText.value!!,
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