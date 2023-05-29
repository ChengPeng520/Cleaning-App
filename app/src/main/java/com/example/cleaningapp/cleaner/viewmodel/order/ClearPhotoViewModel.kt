package com.example.cleaningapp.cleaner.viewmodel.order

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel

class ClearPhotoViewModel : ViewModel() {

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