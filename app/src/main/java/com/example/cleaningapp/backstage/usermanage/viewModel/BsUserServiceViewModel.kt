package com.example.cleaningapp.backstage.usermanage.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.Chat

/**
 * 客服聊天室資料處理
 */
class BsUserServiceViewModel : ViewModel() {
    //原始聊天室列表
    private var chatList = mutableListOf<Chat>()

    // 受監控的LiveData，一旦指派新值就會更新使用者列表畫面
    val chats: MutableLiveData<List<Chat>> by lazy { MutableLiveData<List<Chat>>() }

    init {
        loadChats()
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始使用者列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            chats.value = chatList
        } else {
            val searchChatList = mutableListOf<Chat>()
            chatList.forEach { chat ->
                if (chat.email.contains(newText, true) ||
                    chat.createTime.contains(newText, true)
                ) {
                    searchChatList.add(chat)
                }
            }
            chats.value = searchChatList
        }
    }

    /** 模擬取得遠端資料 */
    private fun loadChats() {
        val chatList = mutableListOf<Chat>()
        chatList.add(
            Chat(
                0,"Rona",
                R.drawable.alb_account_avatar, "rona87@gmail.com", "我是一名熱愛閱讀和旅行的年輕人",
                "02:13", true, true,
            )
        )
        chatList.add(
            Chat(
                1,"Ally",
                R.drawable.alb_account_avatar, "ally87@gmail.com", "我是一位熱衷於技術創新的工程師",
                "09:13", true, true,
            )
        )
        chatList.add(
            Chat(
                2,"Ciyi",
                R.drawable.alb_account_avatar, "ciyi87@gmail.com", "我是一名喜愛運動和音樂的青年",
                "05-09", true, true,
            )
        )
        this.chatList = chatList
        this.chats.value = this.chatList
    }
}