package com.example.cleaningapp.customer.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order

class ApplycomplaintViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }

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