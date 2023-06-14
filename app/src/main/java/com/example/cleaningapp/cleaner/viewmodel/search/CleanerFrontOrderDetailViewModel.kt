package com.example.cleaningapp.cleaner.viewmodel.search

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.OrderPhotos
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.cleaner.uistate.SearchOrderPhotos
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject

class CleanerFrontOrderDetailViewModel : ViewModel() {
    val job: MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }
    val jobPhoto: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }

    // 訂單詳情
    fun fetchOrderAccept(orderId: Int) {
        requestTask<SearchOrderPhotos>(
            "${Constants.BASE_URL}/clnOrder/info0/$orderId",
            "GET",
        )?.let {
            job.value = SearchOrder(
                photo = it.order.photo,
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
            jobPhoto.value = OrderPhotos(photos = it.photos)
        }
    }

    // 確定接單
    fun fetchOrderConfirm(view: View) {
        requestTask<JsonObject>(
            "${Constants.BASE_URL}/orderApplied/",
            "POST",
            reqBody = SearchOrder(
                orderId = job.value?.orderId!!,
                cleanerId = CleanerSharedPreferencesUtils.getCurrentCleanerId()
            )
        )?.let {
            if (it.get("result").asBoolean) {
                Toast.makeText(view.context, "確定接單成功", Toast.LENGTH_SHORT).show()
                view.findNavController()
                    .navigate(R.id.action_cleanerFrontOrderDetailFragment_to_order_acceptFragment)
            }
        }
    }
}