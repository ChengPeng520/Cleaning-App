package com.example.cleaningapp.cleaner.viewmodel.order

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.OrderUtil
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject

class OrderCssignCheckViewModel : ViewModel() {
    val signature: MutableList<ByteArray?> = mutableListOf()
    var orderId: Int = 0

    fun sendSignature(): Boolean {
        Log.d("xxx", "orderId: $orderId")
        requestTask<JsonObject>(
            url = "${Constants.BASE_URL}/clnOrder/",
            method = "PUT",
            reqBody = OrderUtil.OrderStatus(
                OrderUtil.Order(
                    orderId = orderId,
                    status = 4
                ), signature
            )
        )?.let {
            return it.get("result").asBoolean
        }
        return false
    }
}