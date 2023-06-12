package com.example.cleaningapp.backstage.usermanage.viewModel

import android.provider.ContactsContract.Data
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.*
import com.example.cleaningapp.cleaner.uistate.ChatroomItemUiState
import com.example.cleaningapp.cleaner.uistate.ChatroomUiState
import com.example.cleaningapp.share.BackstageSharedPreferencesUtils
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken


class BsUserServiceChatViewModel : ViewModel() {
    val chatroom: MutableLiveData<ChatData> by lazy { MutableLiveData<ChatData>() }
    var cleanerId: Int? = chatroom.value?.cleanerId
    var customerId: Int? = chatroom.value?.customerId

    /*TODO:如果聊天室使用firebase連線,先建立firebase的資料庫連線, 取的 database"chatroom"的路徑
       private val database :FirebaseDataBase = FirebaseDataBase.getInstance()
       private val chatroomRef :DatabaseReference = database.getReference("chatroom")
     */


    private val _uiState = MutableLiveData<Chat>()
    val uiState: LiveData<Chat> = _uiState
    val commitText = MutableLiveData("")

    init {
        fetchChatRoomTalkList()
    }

    /**
     * 連線取得聊天列
     */
    fun fetchChatRoomTalkList() {
        if (chatroom.value?.chatCustBackId != null) {
            // 顧客x後台
            requestTask<List<ChatItem>>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/ChatCustBack/$customerId",
                method = "GET",
                respBodyType = object : TypeToken<List<ChatItem>>() {}.type
            )?.let {
                _uiState.value = Chat(it)
            }
        } else if (chatroom.value?.chatClnBackId != null) {
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
        if (chatroom.value?.chatCustBackId != null) {
            // 顧客x後台
            if (commitText.value.toString().isNotEmpty()) {
                requestTask<JsonObject>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/ChatCustBack",
                    method = "POST",
                    reqBody = ChatItem(
                        cleanerId = null,
                        customerId = customerId,
                        text = commitText.value!!
                    )
                )?.let {
                    if (it.get("result").toString().toBoolean()) {
                        fetchChatRoomTalkList()
                        commitText.value = ""
                    }
                }
            }
        } else if (chatroom.value?.chatClnBackId != null) {
            // 清潔x後台
            if (commitText.value.toString().isNotEmpty()) {
                requestTask<JsonObject>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/ChatClnBack",
                    method = "POST",
                    reqBody = ChatItem(
                        cleanerId = cleanerId,
                        customerId = null,
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