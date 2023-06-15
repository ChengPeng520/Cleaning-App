package com.example.cleaningapp.customer.csCreateOrder

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.CreateOrder
import com.example.cleaningapp.customer.model.CreateOrderPhoto
import com.example.cleaningapp.customer.model.EstablishOrder
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.ImageUtils
import com.example.cleaningapp.share.requestTask

class CsOrderConfirmedViewModel : ViewModel() {
    val orderCreated: MutableLiveData<CreateOrder> by lazy {
        MutableLiveData<CreateOrder>(
            CreateOrder()
        )
    }
    val photo: MutableLiveData<CreateOrderPhoto> by lazy {
        MutableLiveData<CreateOrderPhoto>(
            CreateOrderPhoto()
        )
    }

    fun orderEstablish(view: View) {
        val orderCreated = orderCreated.value
        orderCreated?.customerId = CustomerSharePreferencesUtils.getCurrentCustomerId()
        requestTask<EstablishOrder>(
            url = "${Constants.BASE_URL}/csOrder",
            method = "POST",
            reqBody = orderCreated?.let { EstablishOrder(it, sendPhotos(photo.value!!)) }
        )?.let {
            Navigation.findNavController(view)
                .navigate(R.id.action_csOrderConfirmedFragment_to_csOrderEstablishedFragment)
        } ?: Toast.makeText(view.context, "訂單建立失敗，請稍後再試", Toast.LENGTH_SHORT).show()
    }

    fun convert(value: String): String {
        return if (value.isNotEmpty()) value.substring(0, 5) else ""
    }

    private fun sendPhotos(createOrderPhoto: CreateOrderPhoto): List<ByteArray> {
        val photos = mutableListOf<ByteArray>()
        createOrderPhoto.photo1?.let {
            photos.add(ImageUtils.bitmapToBytes(it))
        }
        createOrderPhoto.photo2?.let {
            photos.add(ImageUtils.bitmapToBytes(it))
        }
        createOrderPhoto.photo3?.let {
            photos.add(ImageUtils.bitmapToBytes(it))
        }
        return photos
    }
}