package com.example.cleaningapp.cleaner.viewmodel.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.Job
import com.example.cleaningapp.cleaner.uistate.OrderChatroomItemUiState
import com.example.cleaningapp.cleaner.uistate.OrderStateUiState
import com.example.cleaningapp.cleaner.uistate.Orderdetail
import kotlinx.coroutines.flow.MutableStateFlow


class CleanerFrontOrderDetailViewModel  : ViewModel() {
    val order: MutableLiveData<Orderdetail> by lazy { MutableLiveData<Orderdetail>()}
    val cleaner :  MutableLiveData<Job> by lazy { MutableLiveData<Job>() }

    init {
        loadOrder()
    }
    private fun loadOrder() {
        order.value = Orderdetail(
            R.drawable.cs_img_xxx,"王先生", 12345678, "臺北市", "中山區", "臺北市中山區南京東路三段219號5樓",
            "2023年4月26日", "12:00-14:00", "12:00", "14:00",5.0,5.0,5.0,5.0,
            "工具:  吸塵器 、拖把\n" +
                    "整理重點:  臥房、客房 ", null, 1111, 0,
            )
    }
}