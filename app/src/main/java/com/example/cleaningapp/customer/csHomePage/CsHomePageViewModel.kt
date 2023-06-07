package com.example.cleaningapp.customer.csHomePage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.OrderRemind
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class CsHomePageViewModel : ViewModel() {
    //    val orders : MutableLiveData<List<Order>> by lazy { MutableLiveData<List<Order>>() }
    val order: MutableLiveData<OrderRemind> by lazy { MutableLiveData<OrderRemind>() }
    val cleaners: MutableLiveData<List<Cleaner>> by lazy { MutableLiveData<List<Cleaner>>() }
    val cleaner: MutableLiveData<Cleaner> by lazy { MutableLiveData<Cleaner>() }

    init {
        fetchCleaners()
        getOrder()
    }

    fun fetchCleaners() {
        requestTask<List<Cleaner>>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied/best",
            respBodyType = object : TypeToken<List<Cleaner>>() {}.type
        )?.let {
            cleaners.value = it
        }
    }

    fun getOrder() {
        requestTask<OrderRemind>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder/remind/4"
        )?.let {
            order.value = it
//            var order = it[0]
//            order = OrderRemind(
//                orderId = order.orderId,
//                areaCity = order.areaCity,
//                areaDistrict = order.areaDistrict,
//                dateOrdered = order.dateOrdered,
//                timeOrderedStart = order.timeOrderedStart,
//                timeOrderedEnd = order.timeOrderedEnd
//            )
        }
    }

}