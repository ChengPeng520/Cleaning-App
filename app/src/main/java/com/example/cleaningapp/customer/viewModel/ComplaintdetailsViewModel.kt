package com.example.cleaningapp.customer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.CompleteOrderPhotos
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.detailed.OrderInfo
import com.example.cleaningapp.share.requestTask

class ComplaintdetailsViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    private val _uiPhoto: MutableLiveData<CompleteOrderPhotos> by lazy { MutableLiveData<CompleteOrderPhotos>() }
    val uiState: LiveData<Order> by lazy { order }
    val uiPhoto: LiveData<CompleteOrderPhotos> by lazy { _uiPhoto }


    //    fun fetchOrdersInfo(orderId: Int) {
//        requestTask<Order>(
//            url = "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder/$orderId",
//            method = "GET",
//        )?.let {
//            order.value = it
//            Log.d("OrderList", "Fetched orders: $it")
//        }
//    }
    fun fetchComplaintInfo(orderId: Int) {
        requestTask<OrderInfo>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder/$orderId",
            "DELETE"
        )?.let {
            order.value = Order(
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
                stars = it.order.stars,
                returnReason = it.order.returnReason
            )
            it.photos?.let { photo ->
                _uiPhoto.value = CompleteOrderPhotos(photo)
            }
        }
    }
}