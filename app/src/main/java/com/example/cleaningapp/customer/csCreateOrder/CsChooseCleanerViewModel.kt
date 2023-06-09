package com.example.cleaningapp.customer.csCreateOrder

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cleaningapp.cleaner.uistate.OrderInfoItemUiState
import com.example.cleaningapp.cleaner.uistate.ProductItemUiState
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.Comment
import com.example.cleaningapp.customer.model.Order
import com.example.cleaningapp.customer.model.OrderEstablished
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class CsChooseCleanerViewModel : ViewModel() {
    private var cleaners = listOf<Cleaner>()
    //受監控的LiveDATA，一旦指派新值就會更新使用者列表畫面
    val cleanerList: MutableLiveData<List<Cleaner>> by lazy { MutableLiveData<List<Cleaner>>() }
    val commentItem: MutableLiveData<Comment> by lazy { MutableLiveData<Comment>() }
    var orderId: Int = 0
    val orderEstablished: MutableLiveData<OrderEstablished> by lazy { MutableLiveData<OrderEstablished>() }
    val orderTest= OrderEstablished()
    //  原始使用者列表



    fun fetchCleanerApplied(orderId: Int) {
        requestTask<List<Cleaner>>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied/choose/$orderId",
            method = "GET",
            respBodyType = object : TypeToken<List<Cleaner>>() {}.type
        )?.let {
            cleanerList.value = it
            cleaners = it
            Log.d("ooo","orderId:$orderId")
            Log.d("xxx","CleanerList:$it")
        }
    }

    fun cancelOrder() {

    }

}