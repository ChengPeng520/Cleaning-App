package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.CompleteOrderInfoUiState
import com.example.cleaningapp.cleaner.uistate.OrderStateUiState
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.cleaner.uistate.Work
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.OrderUtil
import com.example.cleaningapp.share.requestTask

class OrderOrdermatchViewModel : ViewModel() {
//    val _order: MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }
    val order: MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }

//    val uiState: LiveData<OrderStateUiState> = order

//    init {
//        fetchOrderamtch()
//    }
//
//    fun fetchOrderamtch() {
//        requestTask<SearchOrder>(
//            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/info/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
//            "GET"
//        )?.let {
//            order.value = SearchOrder (
//                photo = it.photo,
//                orderId = it.orderId,
//                dateOrdered = it.dateOrdered,
//                timeOrderedStart = it.timeOrderedStart,
//                timeOrderedEnd = it.timeOrderedEnd,
//                areaCity = it.areaCity,
//                areaDistrict = it.areaDistrict,
//                areaDetail = it.areaDetail,
//                livingRoomSize = it.livingRoomSize,
//                kitchenSize = it.kitchenSize,
//                bathRoomSize = it.bathRoomSize,
//                roomSize = it.roomSize,
//                remark = it.remark,
//                priceForCleaner = it.priceForCleaner,
//                status = it.status
//            )
//        }
//    }
}