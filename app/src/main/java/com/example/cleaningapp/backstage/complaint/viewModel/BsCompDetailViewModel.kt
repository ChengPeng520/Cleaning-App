package com.example.cleaningapp.backstage.complaint.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.complaint.model.BSCompOrder
import com.example.cleaningapp.backstage.complaint.model.BSCompOrderInfo
import com.example.cleaningapp.backstage.complaint.model.OrderPhotos
import com.example.cleaningapp.share.requestTask

class BsCompDetailViewModel : ViewModel() {
    private val _uiState: MutableLiveData<BSCompOrder> by lazy { MutableLiveData<BSCompOrder>() }
    private val _uiPhoto: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }
    val uiState: LiveData<BSCompOrder> by lazy { _uiState }
    val uiPhoto: LiveData<OrderPhotos> by lazy { _uiPhoto }

    fun loadComplaint(orderId: Int) {
        requestTask<BSCompOrderInfo>(
            path = "bsOrder/compOrder/$orderId",
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