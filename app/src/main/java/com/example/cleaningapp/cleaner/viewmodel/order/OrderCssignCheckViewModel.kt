package com.example.cleaningapp.cleaner.viewmodel.order

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderCssignCheckViewModel : ViewModel() {
    val signatureBitmap: MutableLiveData<Bitmap> = MutableLiveData()

    fun updateSignatureBitmap(bitmap: Bitmap) {
        signatureBitmap.value = bitmap
    }
}