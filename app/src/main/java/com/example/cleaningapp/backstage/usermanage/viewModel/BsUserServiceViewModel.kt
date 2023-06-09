package com.example.cleaningapp.backstage.usermanage.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.usermanage.model.ChatClnBack
import com.example.cleaningapp.backstage.usermanage.model.ChatData
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

/**
 * 客服聊天室資料處理
 */
class BsUserServiceViewModel : ViewModel() {
    //原始聊天室列表
    private var chatList = listOf<ChatData>()

    // 受監控的LiveData，一旦指派新值就會更新使用者列表畫面
    val chats: MutableLiveData<List<ChatData>> by lazy { MutableLiveData<List<ChatData>>() }
    val chat: MutableLiveData<ChatData> by lazy { MutableLiveData<ChatData>() }

    init {
        loadChatList()
    }

    /**
     * 連線後端取得聊天室列表
     */
    private fun loadChatList() {
        requestTask<List<ChatData>>(
            url = "${Constants.BASE_URL}/ChatClnBack",
            method = "GET",
            respBodyType = object : TypeToken<List<ChatData>>() {}.type
        )?.let {
            chats.value = it
            chatList = it
        }
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始使用者列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            chats.value = chatList
        } else {
            val searchChatList = mutableListOf<ChatData>()
            chatList.forEach { chat ->
                if (chat.email!!.contains(newText, true) ||
                    chat.createTime.contains(newText, true)
                ) {
                    searchChatList.add(chat)
                }
            }
            chats.value = searchChatList
        }
    }

    /** 模擬取得遠端資料 */
//    private fun loadChats() {
//        val chatList = mutableListOf<Chatroom>()
//        chatList.add(
//            Chatroom(
//                chatId = 0,
//                cleanerId = 1,
//                name = "Rona",
//                avatar = R.drawable.alb_account_avatar,
//                email = "rona87@gmail.com",
//                text = "我是一名熱愛閱讀和旅行的年輕人",
//                createTime = "02:13",
//                read = true,
//                closed = true,
//            )
//        )
//        chatList.add(
//            Chatroom(
//                chatId = 1,
//                customerId = 1,
//                name = "Ally",
//                avatar = R.drawable.alb_account_avatar,
//                email = "ally87@gmail.com",
//                text = "我是一位熱衷於技術創新的工程師",
//                createTime = "09:13",
//                read = true,
//                closed = true,
//            )
//        )
//        chatList.add(
//            Chatroom(
//                chatId = 2,
//                customerId = 3,
//                name = "Ciyi",
//                avatar = R.drawable.alb_account_avatar,
//                email = "ciyi87@gmail.com",
//                text = "我是一名喜愛運動和音樂的青年",
//                createTime = "05-09",
//                read = true,
//                closed = true,
//            )
//        )
//        this.chatList = chatList
//        this.chats.value = this.chatList
//    }
}