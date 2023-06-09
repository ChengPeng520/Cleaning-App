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
import com.example.cleaningapp.customer.model.OrderEstablished
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.OrderUtil
import com.example.cleaningapp.share.requestTask

class CsOrderConfirmedViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val photo: MutableLiveData<CreateOrderPhoto> by lazy { MutableLiveData<CreateOrderPhoto>() }


    fun checkout(view: View) {
        requestTask<EstablishOrder>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/orderApplied",
            method = "PUT",
            reqBody = order.value?.let {
                photo.value?.let { photo ->
                    EstablishOrder(
                        order = it,
                        photo = photo
                    )
                }
            }
        )?.let {
                Toast.makeText(view.context, "訂單建立完成", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.action_csOrderEstablishedFragment_to_historicalorderFragment)
            }
                Toast.makeText(view.context, "訂單建立失敗", Toast.LENGTH_SHORT).show()

    }
}