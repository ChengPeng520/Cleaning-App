package com.example.cleaningapp.customer.csCreateOrder

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.CreateOrderPhoto
import com.example.cleaningapp.customer.model.EstablishOrder
import com.example.cleaningapp.customer.model.Order
import com.example.cleaningapp.share.requestTask

class CsOrderConfirmedViewModel : ViewModel() {
    val orderCreated: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val photo: MutableLiveData<CreateOrderPhoto> by lazy { MutableLiveData<CreateOrderPhoto>() }
    var establishOrder: MutableLiveData<EstablishOrder> = MutableLiveData<EstablishOrder>()


    fun orderEstablish(view: View) {
        requestTask<EstablishOrder>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder",
            method = "POST",
            reqBody = orderCreated.value?.let { EstablishOrder(it,photo) }
        )?.let {
                Toast.makeText(view.context, "訂單建立完成", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.action_csOrderEstablishedFragment_to_historicalorderFragment)
            }
                Toast.makeText(view.context, "訂單建立失敗", Toast.LENGTH_SHORT).show()
    }

    fun convert(value: String): String {
        return if (value.isNotEmpty()) value.substring(0, 5) else ""
    }

}