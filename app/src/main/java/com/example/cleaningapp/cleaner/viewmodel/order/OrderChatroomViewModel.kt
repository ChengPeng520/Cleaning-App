package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.*

class OrderChatroomViewModel : ViewModel() {
    private val _orderUiState: MutableLiveData<OrderInfo> by lazy { MutableLiveData<OrderInfo>() }
    val orderUiState: LiveData<OrderInfo> by lazy { _orderUiState }
    private val _chatroomUiState: MutableLiveData<List<OrderChatroomItemUiState>> by lazy { MutableLiveData<List<OrderChatroomItemUiState>>() }
    val chatroomUiState: LiveData<List<OrderChatroomItemUiState>> by lazy { _chatroomUiState }
    val commitText = MutableLiveData("")

    private val talkList = mutableListOf<OrderChatroomItemUiState>()
    private var id = 0

    init {
        talkList.add(OrderChatroomItemUiState(id = id++, fromId = 9, text = "乾是不是我們班太毒阿"))
        talkList.add(OrderChatroomItemUiState(id = id++, fromId = 9, text = "直接毒爆偉銘老師"))
        talkList.add(OrderChatroomItemUiState(id = id++, toId = 21, text = "pizza跟蛋糕怎搞"))
        talkList.add(OrderChatroomItemUiState(id = id++, fromId = 9, text = "對阿"))
        talkList.add(OrderChatroomItemUiState(id = id++, fromId = 9, text = "蛋糕可以先冰冰箱"))
        talkList.add(OrderChatroomItemUiState(id = id++, fromId = 9, text = "pizza可以直接取消嗎"))
        talkList.add(OrderChatroomItemUiState(id = id++, toId = 21, text = "pizza可以改禮拜六"))
        talkList.add(OrderChatroomItemUiState(id = id++, fromId = 9, text = "這個我可以先跟小賴說 請他叫工讀生冰"))
        talkList.add(OrderChatroomItemUiState(id = id++, toId = 21, text = "蛋糕沒解"))
        talkList.add(OrderChatroomItemUiState(id = id++, toId = 21, text = "那可以叫她順便去拿嗎"))
        talkList.add(OrderChatroomItemUiState(id = id++, fromId = 9, text = "喔喔要到現場拿？"))
        talkList.add(OrderChatroomItemUiState(id = id++, fromId = 9, text = "那可能不行欸"))
        talkList.add(OrderChatroomItemUiState(id = id++, toId = 21, text = "黑阿"))
        talkList.add(OrderChatroomItemUiState(id = id++, fromId = 9, text = "說錯 一定不行"))
        talkList.add(OrderChatroomItemUiState(id = id++, toId = 21, text = "還是誰住比較近的"))
        talkList.add(OrderChatroomItemUiState(id = id++, fromId = 9, text = "還是可以跟店家說 晚一天去"))
    }

    fun fetchOrderTalk() {
        _orderUiState.value = OrderInfo("2023年4月25號", "12:00-14:00(2小時)", "房間5坪", 1000)
        _chatroomUiState.value = talkList
    }

    fun commitText() {
        if (commitText.value.toString().isNotEmpty()) {
            talkList.add(
                OrderChatroomItemUiState(
                    id = id++,
                    toId = 1,
                    text = commitText.value.toString()
                )
            )
            _chatroomUiState.value = talkList
            commitText.value = ""
        }
    }
}