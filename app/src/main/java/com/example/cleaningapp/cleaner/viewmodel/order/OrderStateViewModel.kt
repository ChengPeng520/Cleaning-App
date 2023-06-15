package com.example.cleaningapp.cleaner.viewmodel.order

import android.graphics.Bitmap
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.OrderStateUiState
import com.example.cleaningapp.share.CleanerPreferencesUtils
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.OrderUtil
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject

class OrderStateViewModel : ViewModel() {
    private val _uiState: MutableLiveData<OrderStateUiState> by lazy { MutableLiveData<OrderStateUiState>() }
    val uiState: LiveData<OrderStateUiState> = _uiState
    val orderId: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    fun fetchOrderProgress() {
        requestTask<OrderUtil.OrderStatus>(
            url = "${Constants.BASE_URL}/clnOrder/info0/${orderId.value}",
            method = "GET"
        )?.let {
            _uiState.value = OrderStateUiState(
                orderId = it.order.orderId,
                customerId = it.order.customerId,
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
                priceForCleaner = it.order.priceForCleaner,
                status = it.order.status
            )
        }
    }

    fun startCleaning(view: View) {
        requestTask<JsonObject>(
            url = "${Constants.BASE_URL}/clnOrder/",
            method = "PUT",
            reqBody = OrderUtil.OrderStatus(
                OrderUtil.Order(
                    orderId = uiState.value?.orderId!!,
                    status = 2
                ), null
            )
        )?.let {
            if (it.get("result").asBoolean) {
                val order = _uiState.value
                order?.status = 2
                _uiState.value = order
                Toast.makeText(view.context, "訂單開始進行", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(view.context, "進行失敗", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(view.context, "回傳失敗", Toast.LENGTH_SHORT).show()
    }

    fun nextState(view: View) {
        val photosList: List<Bitmap?> =
            CleanerPreferencesUtils.fetchCleaningPhotoFromPreferences(view.context)
        requestTask<JsonObject>(
            url = "${Constants.BASE_URL}/clnOrder/",
            method = "PUT",
            reqBody = OrderUtil.OrderStatus(
                OrderUtil.Order(
                    orderId = uiState.value?.orderId!!,
                    status = 3
                ), CleanerPreferencesUtils.bitmapPhotosToByteArray(photosList)
            )
        )?.let {
            if (it.get("result").asBoolean) {
                val order = _uiState.value
                order?.status = 3
                _uiState.value = order
                CleanerPreferencesUtils.cleanPreferences(view.context)
                Toast.makeText(view.context, "請交由顧客確認", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(view.context, "進行失敗", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(view.context, "回傳失敗", Toast.LENGTH_SHORT).show()
    }
}