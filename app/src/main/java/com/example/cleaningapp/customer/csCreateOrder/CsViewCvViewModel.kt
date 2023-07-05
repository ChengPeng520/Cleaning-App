package com.example.cleaningapp.customer.csCreateOrder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.Comment
import com.example.cleaningapp.customer.model.OrderEstablished
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class CsViewCvViewModel : ViewModel() {
    //受監控的LiveDATA，一旦指派新值就會更新使用者列表畫面
    val comments: MutableLiveData<List<Comment>> by lazy { MutableLiveData<List<Comment>>() }
    val cleaner: MutableLiveData<Cleaner> by lazy { MutableLiveData<Cleaner>() }
    val orderEstablished: MutableLiveData<OrderEstablished> by lazy {
        MutableLiveData<OrderEstablished>(
            OrderEstablished()
        )
    }
    var csPayment: Int = 0

    fun fetchCleanerInfo(cleanerId: Int) {
        requestTask<Cleaner>(
            "AccountBackstage/2/$cleanerId"
        )?.let {
            cleaner.value = it
        }
    }

    fun fetchComments(cleanerId: Int) {
        requestTask<List<Comment>>(
            "orderApplied/comment/$cleanerId",
            respBodyType = object : TypeToken<List<Comment>>() {}.type
        )?.let {
            comments.value = it
        }
    }

    fun fetchOrdersInfo(orderId: Int) {
        requestTask<Order>(
            path = "csOrder/$orderId",
            method = "GET",
        )?.let {
            csPayment = it.priceForCustomer
            Log.d("yyy", "csPayment: $csPayment")
        }
    }

    fun checkout(): Boolean {
        requestTask<JsonObject>(
            "orderApplied",
            "PUT",
            orderEstablished.value
        )?.let {
            return it.get("result").asBoolean
        }
        return false
    }
}