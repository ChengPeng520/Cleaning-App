package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.CompleteOrderInfoUiState
import com.example.cleaningapp.cleaner.uistate.CompleteOrderPhotos
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.OrderUtil
import com.example.cleaningapp.share.requestTask

class CompleteOrderInfoViewModel : ViewModel() {
    private val _uiState: MutableLiveData<CompleteOrderInfoUiState> by lazy { MutableLiveData<CompleteOrderInfoUiState>() }
    private val _uiPhoto: MutableLiveData<CompleteOrderPhotos> by lazy { MutableLiveData<CompleteOrderPhotos>() }
    val uiState: LiveData<CompleteOrderInfoUiState> by lazy { _uiState }
    val uiPhoto: LiveData<CompleteOrderPhotos> by lazy { _uiPhoto }

    fun fetchOrderInfo(orderId : Int) {
        requestTask<OrderUtil.InsertOrder>(
            "${Constants.BASE_URL}/clnOrder/info2/$orderId",
            "GET"
        )?.let {
            _uiState.value = CompleteOrderInfoUiState(
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
                priceForCleaner = it.order.priceForCleaner,
                stars = it.order.stars,
                commentCleaner = it.order.commentCleaner!!
            )
            it.photos?.let { photo ->
                _uiPhoto.value = CompleteOrderPhotos(photo)
            }
        }
    }
}