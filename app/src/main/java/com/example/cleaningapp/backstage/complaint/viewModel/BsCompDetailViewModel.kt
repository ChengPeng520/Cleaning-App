package com.example.cleaningapp.backstage.complaint.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.complaint.model.*
import com.example.cleaningapp.cleaner.uistate.CompleteOrderInfoUiState
import com.example.cleaningapp.cleaner.uistate.CompleteOrderPhotos
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class BsCompDetailViewModel : ViewModel() {
    private val _uiState: MutableLiveData<BSCompOrder> by lazy { MutableLiveData<BSCompOrder>() }
    private val _uiPhoto: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }
    val uiState: LiveData<BSCompOrder> by lazy { _uiState }
    val uiPhoto: LiveData<OrderPhotos> by lazy { _uiPhoto }

    fun loadComplaint(orderId: Int) {
        requestTask<BSCompOrderInfo>(
            url = "${Constants.BASE_URL}/bsOrder/compOrder/$orderId",
            method = "GET"
        )?.let {
            _uiState.value = BSCompOrder(
                orderId = it.bsCompOrder.orderId,
                customerName = it.bsCompOrder.customerName,
                timeCreate = it.bsCompOrder.timeCreate,
                cleanerName = it.bsCompOrder.cleanerName,
                status = it.bsCompOrder.status,
                timeUpdate = it.bsCompOrder.timeUpdate,
                returnReason = it.bsCompOrder.returnReason,
                bsComplainRemark = it.bsCompOrder.bsComplainRemark
            )
            it.orderPhotos?.let { photo ->
                _uiPhoto.value = OrderPhotos(photo)
            }
        }
    }
}