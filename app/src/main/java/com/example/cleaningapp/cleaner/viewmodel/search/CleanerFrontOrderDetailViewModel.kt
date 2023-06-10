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
//    val cleaner: MutableLiveData<Job> by lazy { MutableLiveData<Job>() }
    val job: MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }
    val jobPhoto: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }
//    val job: LiveData<SearchOrder> by lazy { _job }
//    val jobPhoto: LiveData<OrderPhotos> by lazy { _jobPhoto }

    // 訂單詳情
    fun fetchOrderAccept(orderId: Int) {
        requestTask<SearchOrder>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/info/$orderId",
            "GET",
        )?.let {
            job.value = it
        }

        requestTask<SearchOrderPhotos>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/info/$orderId",
            "GET",
        )?.let {
            jobPhoto.value = OrderPhotos(it.photos)
        }
    }


    // 確定接單
    fun fetchOrderConfirm() {
        requestTask<SearchOrder>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied/",
            "POST"
        )?.let {
            job.value = it
        }
        requestTask<SearchOrderPhotos>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/orderApplied/",
            "GET",
        )?.let {
            jobPhoto.value = OrderPhotos(it.photos)
        }
    }
}
