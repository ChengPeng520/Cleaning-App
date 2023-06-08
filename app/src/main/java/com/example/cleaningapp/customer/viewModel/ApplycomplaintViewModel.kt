package com.example.cleaningapp.customer.viewModel

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.detailed.OrderInfo
import com.example.cleaningapp.share.requestTask

class ApplycomplaintViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val complaint: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val orderId: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    //  拍照功能
    val photo1: MutableLiveData<Bitmap?> by lazy { MutableLiveData<Bitmap?>(null) }
    val photo2: MutableLiveData<Bitmap?> by lazy { MutableLiveData<Bitmap?>(null) }
    val photo3: MutableLiveData<Bitmap?> by lazy { MutableLiveData<Bitmap?>(null) }
    fun addCapturedPhoto(photo: Bitmap?) {
        if (photo1.value == null) {
            photo1.value = photo
        } else if (photo2.value == null) {
            photo2.value = photo
        } else if (photo3.value == null) {
            photo3.value = photo
        }
    }

    fun fetchOrdersInfo(orderId: Int) {
        requestTask<Order>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder/$orderId",
            method = "GET",
        )?.let {
            order.value = it
//            Log.d("OrderList", "Fetched orders: $it")
        }
    }

    fun uploadComplaint(view: View) {

        requestTask<OrderInfo>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder/",
            method = "PUT",
            reqBody = OrderInfo(
                Order(
                    orderId = order.value!!.orderId,
                    bsComplainRemark = complaint.value!!,
                    cleanerId = order.value!!.cleanerId,
                    commentCleaner = order.value!!.commentCleaner,
                    stars = order.value!!.stars,
                    status = 7
                ), null
            )
        )?.let {
            val bundle = Bundle()
            bundle.putInt("orderId", orderId.value!!)
            view.findNavController().navigate(
                R.id.action_applycomplaintFragment_to_complaintdetailsFragment,
                bundle
            )
        }
    }
}