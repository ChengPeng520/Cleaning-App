package com.example.cleaningapp.customer.csCreateOrder

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Coupon
import com.example.cleaningapp.customer.model.Order

class CsCreateOrderViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>() }
    val textDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textTimeBegin: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textTimeEnd: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val tvCsCreateOrderChooseCoupon: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private val capturedPhotos: MutableList<Bitmap?> = mutableListOf()

    val capturedCount: Int
        get() = capturedPhotos.size

    fun addCapturedPhoto(photo: Bitmap?) {
        capturedPhotos.add(photo)
    }

    fun isPhotoExists(photo: Bitmap?): Boolean {
        for (capturedPhoto in capturedPhotos) {
            if (capturedPhoto != null && capturedPhoto.sameAs(photo)) {
                return true
            }
        }
        return false
    }

}