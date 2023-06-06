package com.example.cleaningapp.cleaner.viewmodel.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.Job
import com.example.cleaningapp.cleaner.uistate.OrderPhotos
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.cleaner.uistate.SearchOrderPhotos
import com.example.cleaningapp.share.requestTask


class CleanerFrontOrderDetailViewModel : ViewModel() {
    val cleaner: MutableLiveData<Job> by lazy { MutableLiveData<Job>() }
    private val _job: MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }
    private val _jobPhoto: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }
    val job: LiveData<SearchOrder> = _job
    val jobPhoto: LiveData<OrderPhotos> by lazy { _jobPhoto }

    // 訂單詳情
    fun fetchOrderAccept(orderId: Int) {
        requestTask<SearchOrderPhotos>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/info/$orderId",
            "GET"
        )?.let {
            _job.value = SearchOrder(
                orderId = it.order.orderId,
                dateOrdered = it.order.dateOrdered,
                timeOrderedStart = it.order.timeOrderedStart,
                timeOrderedEnd = it.order.timeOrderedEnd,
                areaCity = it.order.areaCity,
                areaDistrict = it.order.areaDistrict,
                photo = it.order.photo
            )
            _jobPhoto.value = OrderPhotos(it.photos)
        }
    }

    // 確定接單
    fun fetchOrderConfirm() {
        requestTask<SearchOrder>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied/",
            "POST"
        )?.let {
            _job.value = SearchOrder(
                orderId = it.orderId,
                dateOrdered = it.dateOrdered,
                timeOrderedStart = it.timeOrderedStart,
                timeOrderedEnd = it.timeOrderedEnd,
                areaCity = it.areaCity,
                areaDistrict = it.areaDistrict,
                photo = it.photo,
                areaDetail = it.areaDetail,
                livingRoomSize = it.livingRoomSize,
                kitchenSize = it.kitchenSize,
                bathRoomSize = it.bathRoomSize,
                roomSize = it.roomSize,
                remark = it.remark,
                priceForCleaner = it.priceForCleaner,
                status = it.status,
            )
        }
    }
}