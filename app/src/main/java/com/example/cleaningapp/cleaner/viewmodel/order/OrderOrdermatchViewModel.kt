package com.example.cleaningapp.cleaner.viewmodel.order

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.OrderUtil
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject

class OrderOrdermatchViewModel : ViewModel() {
    val order: MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    var orderId: Int = 0

    fun fetchOrderMatch(orderId: Int) {
        requestTask<OrderUtil.InsertOrder>(
            "${Constants.BASE_URL}/clnOrder/info0/$orderId",
            "GET"
        )?.let {
            order.value = SearchOrder(
                orderId = it.order.orderId,
                dateOrdered = it.order.dateOrdered,
                timeOrderedStart = it.order.timeOrderedStart,
                timeOrderedEnd = it.order.timeOrderedEnd,
                areaCity = it.order.areaCity,
                areaDistrict = it.order.areaDistrict,
                areaDetail = it.order.areaDetail,
                livingRoomSize = it.order.livingRoomSize,
                kitchenSize = it.order.kitchenSize,
                bathRoomSize = it.order.bathRoomSize,
                roomSize = it.order.roomSize,
                remark = it.order.remark,
                priceForCleaner = it.order.priceForCleaner
            )
        }
    }

    fun deleteOrder(view: View) {
        requestTask<JsonObject>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied/${orderId}/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
            "DELETE"
        )?.let {
            if (it.get("result").asBoolean) {
                Toast.makeText(view.context, "取消接單成功", Toast.LENGTH_SHORT).show()
                view.findNavController().popBackStack()
            }
        }
    }
}

