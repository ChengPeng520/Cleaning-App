package com.example.cleaningapp.customer.detailed

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderListViewModel : ViewModel() {
    val orderItem: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val photoUri: MutableLiveData<Uri?> by lazy { MutableLiveData<Uri?>() }

    fun setPhotoUri(uri: Uri?) {
        photoUri.value = uri
    }
}