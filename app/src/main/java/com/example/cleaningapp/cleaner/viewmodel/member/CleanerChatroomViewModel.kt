package com.example.cleaningapp.cleaner.viewmodel.member

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.ChatroomItemUiState
import com.example.cleaningapp.cleaner.uistate.ChatroomUiState

class CleanerChatroomViewModel : ViewModel() {
    private val _uiState = MutableLiveData<ChatroomUiState>()
    val uiState: LiveData<ChatroomUiState> = _uiState

    fun fetchChatRoomTalkList() {
        val talkList = mutableListOf<ChatroomItemUiState>()
        talkList.add(ChatroomItemUiState(id = 1, fromId = 1, text = "您好，近期有一單客訴問題"))
        talkList.add(ChatroomItemUiState(id = 2, toId = 1, text = "請問是什麼時候的單?"))
        talkList.add(ChatroomItemUiState(id = 3, fromId = 1, text = "是a423415這單喔 顧客說你打掃很爛"))
        talkList.add(ChatroomItemUiState(id = 4, toId = 1, text = "沒關係，給我顧客地址我會把她殺掉的"))
        _uiState.value = ChatroomUiState(talkList)
    }
}