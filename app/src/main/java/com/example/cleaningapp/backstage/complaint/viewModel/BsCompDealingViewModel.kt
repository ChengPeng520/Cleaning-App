package com.example.cleaningapp.backstage.complaint.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.complaint.model.*
import com.example.cleaningapp.share.requestTask

class BsCompDealingViewModel : ViewModel() {
    val _uiState: MutableLiveData<BSCompOrder> by lazy { MutableLiveData<BSCompOrder>() }
    private val _uiPhoto: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }
    val uiState: LiveData<BSCompOrder> by lazy { _uiState }
    val uiPhoto: LiveData<OrderPhotos> by lazy { _uiPhoto }

    /**
     * 連線後端取得客訴詳細資料
     */
    fun loadComplaint(orderId: Int) {
        requestTask<BSCompOrderInfo>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/bsOrder/compOrder/$orderId",
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

    /**
     * 修改客訴訂單資料
     */
    fun editComplaint(orderId: Int) {
        _uiState.value?.let {
            it.orderId = orderId
            it.status = 7
            requestTask<BSCompOrder>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/bsOrder/",
                method = "PUT",
                reqBody = it
            )
        }
    }
}