package com.example.cleaningapp.customer.csCreateOrder

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Coupon
import com.example.cleaningapp.customer.model.CreateOrder
import com.example.cleaningapp.customer.model.CreateOrderPhoto

class CsCreateOrderViewModel : ViewModel() {
    val order: MutableLiveData<CreateOrder> by lazy { MutableLiveData<CreateOrder>(CreateOrder()) }
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>() }
    val photo: MutableLiveData<CreateOrderPhoto> by lazy {
        MutableLiveData<CreateOrderPhoto>(
            CreateOrderPhoto()
        )
    }
    val countyPosition: MutableLiveData<Int> by lazy { MutableLiveData<Int>(0) }
    val districtPosition: MutableLiveData<Int> by lazy { MutableLiveData<Int>(0) }

    fun addCapturedPhoto(photo: Bitmap?) {
        this.photo.value?.let {
            if (it.photo1 == null) {
                it.photo1 = photo
            } else if (it.photo2 == null) {
                it.photo2 = photo
            } else if (it.photo3 == null) {
                it.photo3 = photo
            }
            this.photo.value = it
        }
    }

    fun convert(value: String): String {
        return if (value.isNotEmpty()) value.substring(0, 5) else ""
    }
}