package com.example.cleaningapp.customer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.chatroom.ChatroomItemUiState
import com.example.cleaningapp.customer.chatroom.ChatroomUiState


class ChatRoomViewModel : ViewModel() {
    private val _uiState = MutableLiveData<ChatroomUiState>()
    private var id = 0
    private val talkList = mutableListOf<ChatroomItemUiState>()
    val uiState: LiveData<ChatroomUiState> = _uiState
    val commitText = MutableLiveData("")

    init {
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "您好，請問有什麼問題可以協助幫忙呢?"))
        talkList.add(ChatroomItemUiState(id = id++, toId = 21, text =
                    "我立刻要投訴那個王八\n" +
                    "我立刻要投訴那個王八\n" +
                    "我立刻要投訴那個王八\n" +
                    "我立刻要投訴那個王八\n" +
                    "我立刻要投訴那個王八\n" +
                    "我立刻要投訴那個王八"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "您好，請問訂單編號是多少呢?"))
        talkList.add(ChatroomItemUiState(id = id++, toId = 21, text = "訂單編號:XXXXXXXX"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "您好，請問您要申請客訴嗎?"))
        talkList.add(ChatroomItemUiState(id = id++, toId = 21, text = "是的"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "那請點選下列 申請客訴"))
    }

    fun fetchChatRoomTalkList() {
        _uiState.value = ChatroomUiState(talkList)
    }

    fun commitText() {
        if (commitText.value.toString().isNotEmpty()) {
            talkList.add(
                ChatroomItemUiState(
                    id = id++,
                    toId = 1,
                    text = commitText.value.toString()
                )
            )
            _uiState.value = ChatroomUiState(talkList)
            commitText.value = ""
        }
    }
}