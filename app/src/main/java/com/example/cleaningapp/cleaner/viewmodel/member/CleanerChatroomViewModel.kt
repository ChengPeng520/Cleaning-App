package com.example.cleaningapp.cleaner.viewmodel.member

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.ChatroomItemUiState
import com.example.cleaningapp.cleaner.uistate.ChatroomUiState

class CleanerChatroomViewModel : ViewModel() {
    private val _uiState = MutableLiveData<ChatroomUiState>()
    private var id = 0
    private val talkList = mutableListOf<ChatroomItemUiState>()
    val uiState: LiveData<ChatroomUiState> = _uiState
    val commitText = MutableLiveData("")

    init {
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "乾是不是我們班太毒阿"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "直接毒爆偉銘老師"))
        talkList.add(ChatroomItemUiState(id = id++, toId = 21, text = "pizza跟蛋糕怎搞"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "對阿"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "蛋糕可以先冰冰箱"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "pizza可以直接取消嗎"))
        talkList.add(ChatroomItemUiState(id = id++, toId = 21, text = "pizza可以改禮拜六"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "這個我可以先跟小賴說 請他叫工讀生冰"))
        talkList.add(ChatroomItemUiState(id = id++, toId = 21, text = "蛋糕沒解"))
        talkList.add(ChatroomItemUiState(id = id++, toId = 21, text = "那可以叫她順便去拿嗎"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "喔喔要到現場拿？"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "那可能不行欸"))
        talkList.add(ChatroomItemUiState(id = id++, toId = 21, text = "黑阿"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "說錯 一定不行"))
        talkList.add(ChatroomItemUiState(id = id++, toId = 21, text = "還是誰住比較近的"))
        talkList.add(ChatroomItemUiState(id = id++, fromId = 9, text = "還是可以跟店家說 晚一天去"))
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