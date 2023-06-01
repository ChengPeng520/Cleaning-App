package com.example.cleaningapp.backstage.usermanage.viewModel

import android.provider.ContactsContract.Data
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.Chat
import com.example.cleaningapp.backstage.usermanage.model.ChatItem
import com.example.cleaningapp.backstage.usermanage.model.Chatroom
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.cleaner.uistate.ChatroomItemUiState
import com.example.cleaningapp.cleaner.uistate.ChatroomUiState


class BsUserServiceChatViewModel : ViewModel() {
    val chat: MutableLiveData<Chat> by lazy { MutableLiveData<Chat>() }
    val chatroom: MutableLiveData<Chatroom> by lazy { MutableLiveData<Chatroom>() }

    /*TODO:如果聊天室使用firebase連線,先建立firebase的資料庫連線, 取的 database"chatroom"的路徑
       private val database :FirebaseDataBase = FirebaseDataBase.getInstance()
       private val chatroomRef :DatabaseReference = database.getReference("chatroom")
     */


    private val _uiState = MutableLiveData<Chat>()
    private var id = 0
    private val talkList = mutableListOf<ChatItem>()
    val uiState: LiveData<Chat> = _uiState
    val commitText = MutableLiveData("")

    init {
        talkList.add(ChatItem(id = id++, fromId = 9, text = "乾是不是我們班太毒阿"))
        talkList.add(ChatItem(id = id++, fromId = 9, text = "直接毒爆偉銘老師"))
        talkList.add(ChatItem(id = id++, toId = 21, text = "pizza跟蛋糕怎搞"))
        talkList.add(ChatItem(id = id++, fromId = 9, text = "對阿"))
        talkList.add(ChatItem(id = id++, fromId = 9, text = "蛋糕可以先冰冰箱"))
        talkList.add(ChatItem(id = id++, fromId = 9, text = "pizza可以直接取消嗎"))
        talkList.add(ChatItem(id = id++, toId = 21, text = "pizza可以改禮拜六"))
        talkList.add(ChatItem(id = id++, fromId = 9, text = "這個我可以先跟小賴說 請他叫工讀生冰"))
        talkList.add(ChatItem(id = id++, toId = 21, text = "蛋糕沒解"))
        talkList.add(ChatItem(id = id++, toId = 21, text = "那可以叫她順便去拿嗎"))
        talkList.add(ChatItem(id = id++, fromId = 9, text = "喔喔要到現場拿？"))
        talkList.add(ChatItem(id = id++, fromId = 9, text = "那可能不行欸"))
        talkList.add(ChatItem(id = id++, toId = 21, text = "黑阿"))
        talkList.add(ChatItem(id = id++, fromId = 9, text = "說錯 一定不行"))
        talkList.add(ChatItem(id = id++, toId = 21, text = "還是誰住比較近的"))
        talkList.add(ChatItem(id = id++, fromId = 9, text = "還是可以跟店家說 晚一天去"))
    }

    fun fetchChatRoomTalkList() {
        _uiState.value = Chat(talkList)
    }

    fun commitText() {
        if (commitText.value.toString().isNotEmpty()) {
            talkList.add(
                ChatItem(
                    id = id++,
                    toId = 1,
                    text = commitText.value.toString()
                )
            )
            _uiState.value = Chat(talkList)
            commitText.value = ""
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