package com.example.cleaningapp.backstage.complaint.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.complaint.model.BSCompOrder
import com.example.cleaningapp.backstage.complaint.model.BSCompOrderItem
import com.example.cleaningapp.backstage.complaint.model.Complaint
import com.example.cleaningapp.backstage.complaint.model.OrderPhotos
import com.example.cleaningapp.share.requestTask

class BsCompDealingViewModel : ViewModel() {
    private val _uiState: MutableLiveData<BSCompOrderItem> by lazy { MutableLiveData<BSCompOrderItem>() }
    private val _uiPhoto: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }
    val uiState: LiveData<BSCompOrderItem> by lazy { _uiState }
    val uiPhoto: LiveData<OrderPhotos> by lazy { _uiPhoto }

    fun loadComplaint(orderId: Int) {
        requestTask<BSCompOrder>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/bsOrder/compOrder/$orderId",
            method = "GET"
        )?.let {
            _uiState.value = BSCompOrderItem(
                orderId = it.bsCompOrderItem.orderId,
                customerName = it.bsCompOrderItem.customerName,
                timeCreate = it.bsCompOrderItem.timeCreate,
                cleanerName = it.bsCompOrderItem.cleanerName,
                status = it.bsCompOrderItem.status,
                timeUpdate = it.bsCompOrderItem.timeUpdate,
                returnReason = it.bsCompOrderItem.returnReason,
                bsComplainRemark = it.bsCompOrderItem.bsComplainRemark
            )
            it.orderPhotos?.let { photo ->
                _uiPhoto.value = OrderPhotos(photo)
            }
        }
    }

    //TODO
    fun editComplaint(orderId: Int){
//        requestTask<BSCompOrder>(
//            url = "http://10.0.2.2:8080/javaweb-cleaningapp/bsOrder/compOrder/$orderId"
//        )
    }
}