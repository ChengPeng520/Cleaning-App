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
        if (customerId != 0) {
            Log.d("1", "1")
            // 顧客x後台
            requestTask<List<ChatItem>>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/ChatCustBack/$customerId",
                method = "GET",
                respBodyType = object : TypeToken<List<ChatItem>>() {}.type
            )?.let {
                _uiState.value = Chat(it)
            }
        } else if (cleanerId != 0) {
            Log.d("2", "2")
            // 清潔x後台
            requestTask<List<ChatItem>>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/ChatClnBack/$cleanerId",
                method = "GET",
                respBodyType = object : TypeToken<List<ChatItem>>() {}.type
            )?.let {
                _uiState.value = Chat(it)
            }
        }
    }

    fun commitText() {
        if (customerId != 0) {
            // 顧客x後台
            if (commitText.value.toString().isNotEmpty()) {
                requestTask<JsonObject>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/ChatCustBack",
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
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/ChatClnBack",
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

/*      TODO: 連線標記是否有成功的方法
        fun  makeChatroomAsClosed(chatroomId:Int){
        val chatroomRef =
        val closeRef =chatroomRef.child(chatroomId).child("closed")
        closeRef.setValue(true).addOnSuccessListener{
            Toast.makeText(closeRef,"已成功標記",Toast.LENGTH_SHORT,).show()
     }
            .addOnFailureListener{  error->
                Toast.makeText(closeRef,"標記失敗",Toast.LENGTH_SHORT,).show()
     }
 }
 */
}