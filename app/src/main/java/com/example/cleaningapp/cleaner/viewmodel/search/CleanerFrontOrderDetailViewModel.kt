package com.example.cleaningapp.cleaner.viewmodel.search

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.cleaningapp.cleaner.uistate.OrderPhotos
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.cleaner.uistate.SearchOrderPhotos
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject


class CleanerFrontOrderDetailViewModel : ViewModel() {
    val job: MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }
    val jobPhoto: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }

    // 訂單詳情
    fun fetchOrderAccept(orderId: Int) {
        requestTask<SearchOrderPhotos>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/info/$orderId",
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
//            Log.d("photo","$jobPhoto")
        }
    }


    // 確定接單
    fun fetchOrderConfirm(view: View) {
        requestTask<JsonObject>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied/",
            "POST",
        )?.let {
            if (it.get("result").asBoolean) {
                Toast.makeText(view.context, "確定接單成功", Toast.LENGTH_SHORT).show()
                view.findNavController()

            }
        }
    }
}
