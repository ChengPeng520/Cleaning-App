package com.example.cleaningapp.customer.csCreateOrder

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Coupon
import com.example.cleaningapp.customer.model.Order

class CsCreateOrderViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>() }
    val textDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textTimeBegin: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textTimeEnd: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val tvUseCoupon: MutableLiveData<String> by lazy { MutableLiveData<String>() }

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
}