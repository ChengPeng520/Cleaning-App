package com.example.cleaningapp.customer.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order

class ApplycomplaintViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
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