package com.example.cleaningapp.customer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.CompleteOrderPhotos
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.detailed.OrderInfo
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask

class ComplaintdetailsViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    private val _uiPhoto: MutableLiveData<CompleteOrderPhotos> by lazy { MutableLiveData<CompleteOrderPhotos>() }
    val uiState: LiveData<Order> by lazy { order }
    val uiPhoto: LiveData<CompleteOrderPhotos> by lazy { _uiPhoto }

    fun fetchComplaintInfo(orderId: Int) {
        requestTask<OrderInfo>(
            "${Constants.BASE_URL}/csOrder/$orderId",
            "DELETE"
        )?.let {
            order.value = Order(
                orderId = orderId,
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