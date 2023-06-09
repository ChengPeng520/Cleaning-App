package com.example.cleaningapp.customer.csUserPage

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.CustomerSharePreferencesUtils

class CsUserPageViewModel : ViewModel() {
    private val _photo: MutableLiveData<Bitmap> by lazy { MutableLiveData<Bitmap>() }
    val photo: LiveData<Bitmap> by lazy { _photo }

    init {
        fetchPhoto()
    }

    fun fetchPhoto() {
        _photo.value = CustomerSharePreferencesUtils.getCurrentCustomerPhoto()
    }
}