package com.example.cleaningapp.backstage.usermanage.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.usermanage.model.Chat
import com.example.cleaningapp.backstage.usermanage.model.ChatData
import com.example.cleaningapp.backstage.usermanage.model.ChatItem
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class BsUserServiceChatViewModel : ViewModel() {
    val chatroom: MutableLiveData<ChatData> by lazy { MutableLiveData<ChatData>() }
    var cleanerId: Int = 0
    var customerId: Int = 0
    private val _uiState = MutableLiveData<Chat>()
    val uiState: LiveData<Chat> = _uiState
    val commitText = MutableLiveData("")

    /**
     * 連線取得聊天列
     */
    fun fetchChatRoomTalkList() {
        requestTask<List<ChatItem>>(
            path = "ChatClnBack/$cleanerId",
            method = "GET",
            respBodyType = object : TypeToken<List<ChatItem>>() {}.type
        )?.let {
            _uiState.value = Chat(it)
            if (customerId != 0) {
                Log.d("1", "1")
                // 顧客x後台
                requestTask<List<ChatItem>>(
                    path = "ChatCustBack/$customerId",
                    method = "GET",
                    respBodyType = object : TypeToken<List<ChatItem>>() {}.type
                )?.let {
                    _uiState.value = Chat(it)
                }
            }
            if (cleanerId != 0) {
                Log.d("2", "2")
                // 清潔x後台
                requestTask<List<ChatItem>>(
                    path = "ChatClnBack/$cleanerId",
                    method = "GET",
                    respBodyType = object : TypeToken<List<ChatItem>>() {}.type
                )?.let {
                    _uiState.value = Chat(it)
                }
            }
        }
    }

    fun commitText() {
        if (commitText.value.toString().isNotEmpty()) {
            requestTask<JsonObject>(
                path = "ChatClnBack",
                method = "POST",
                reqBody = ChatItem(
                    msgCustBackId = 1,
                    backstageId = 1,
                    text = commitText.value!!
                )
            )?.let {
                if (it.get("result").toString().toBoolean()) {
                    fetchChatRoomTalkList()
                    commitText.value = ""
                    if (customerId != 0) {
                        // 顧客x後台
                        if (commitText.value.toString().isNotEmpty()) {
                            requestTask<JsonObject>(
                                path = "ChatCustBack",
                                method = "POST",
                                reqBody = ChatItem(
                                    text = commitText.value!!
                                )
                            )?.let {
                                if (it.get("result").toString().toBoolean()) {
                                    fetchChatRoomTalkList()
                                    commitText.value = ""
                                }
                            }
                        }
                    } else if (cleanerId != 0) {
                        // 清潔x後台
                        if (commitText.value.toString().isNotEmpty()) {
                            requestTask<JsonObject>(
                                path = "ChatClnBack",
                                method = "POST",
                                reqBody = ChatItem(
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
            }
        }
    }
}