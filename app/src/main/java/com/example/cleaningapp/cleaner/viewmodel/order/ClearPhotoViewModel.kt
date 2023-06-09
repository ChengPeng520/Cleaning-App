package com.example.cleaningapp.cleaner.viewmodel.order

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.OrderPhotos
import com.example.cleaningapp.cleaner.uistate.SearchOrderPhotos
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class ClearPhotoViewModel : ViewModel() {
    val photos: MutableList<Bitmap?> = MutableList(3) { null }
    val cleanerPhoto: MutableLiveData<SearchOrderPhotos> by lazy { MutableLiveData<SearchOrderPhotos>() }
    val orderPhotos: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }

    fun cleanerBeforePhoto(orderId: Int) {
        requestTask<SearchOrderPhotos>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/info/$orderId",
            "GET"
        )?.let {
            orderPhotos.value = OrderPhotos(it.photos)
        }
    }
}