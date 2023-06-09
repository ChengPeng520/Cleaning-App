package com.example.cleaningapp.customer.csCreateOrder

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.Comment
import com.example.cleaningapp.customer.model.OrderEstablished
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class CsViewCvViewModel : ViewModel() {
    //受監控的LiveDATA，一旦指派新值就會更新使用者列表畫面
    val comments: MutableLiveData<List<Comment>> by lazy { MutableLiveData<List<Comment>>() }
    val cleaner: MutableLiveData<Cleaner> by lazy { MutableLiveData<Cleaner>() }
    val orderEstablished: MutableLiveData<OrderEstablished> by lazy { MutableLiveData<OrderEstablished>() }
    var csPayment: Int = 0

    fun fetchCleanerInfo(cleanerId: Int) {
        requestTask<Cleaner>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/2/$cleanerId"
        )?.let {
            cleaner.value = it
        }
    }

    fun fetchComments(cleanerId: Int) {
        requestTask<List<Comment>>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied/comment/$cleanerId",
            respBodyType = object : TypeToken<List<Comment>>() {}.type
        )?.let {
            comments.value = it
        }
    }

    fun fetchOrdersInfo(orderId: Int) {
        requestTask<Order>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder/$orderId",
            method = "GET",
        )?.let {
            csPayment = it.priceForCustomer
        }
    }

    fun checkout(requireContext: Context) {
        requestTask<OrderEstablished>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied",
            "PUT",
            orderEstablished.value
        )?.let {
        }
    }
}