package com.example.cleaningapp.cleaner.viewmodel.order

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.CreateOrderPhotos
import com.example.cleaningapp.cleaner.uistate.OrderPhotos
import com.example.cleaningapp.cleaner.uistate.SearchOrderPhotos
import com.example.cleaningapp.share.requestTask

class ClearPhotoViewModel : ViewModel() {
    val photos: MutableList<Bitmap?> = MutableList(3) { null }
    val cleanerPhoto: MutableLiveData<SearchOrderPhotos> by lazy { MutableLiveData<SearchOrderPhotos>() }
    val orderPhotos: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }
    val photo: MutableLiveData<CreateOrderPhotos> by lazy {
        MutableLiveData<CreateOrderPhotos>(
            CreateOrderPhotos()
        )
    }

    fun cleanerBeforePhoto(orderId: Int) {
        requestTask<SearchOrderPhotos>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/info0/$orderId",
            "GET"
        )?.let {
            orderPhotos.value = OrderPhotos(it.photos)
        }
    }

    fun addCapturedPhoto(photo: Bitmap?) {
        this.photo.value?.let {
            if (it.photo1 == null) {
                it.photo1 = photo
                photos[0] = photo
            } else if (it.photo2 == null) {
                it.photo2 = photo
                photos[1] = photo
            } else if (it.photo3 == null) {
                it.photo3 = photo
                photos[2] = photo
            }
            this.photo.value = it
        }
    }
}