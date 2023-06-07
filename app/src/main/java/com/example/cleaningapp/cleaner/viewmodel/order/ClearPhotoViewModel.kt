package com.example.cleaningapp.cleaner.viewmodel.order

import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.OrderPhotos
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.cleaner.uistate.SearchOrderPhotos
import com.example.cleaningapp.customer.model.CreateOrderPhoto
import com.example.cleaningapp.share.CleanerPreferencesUtils
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask

class ClearPhotoViewModel : ViewModel() {
    val capturedPhotos: MutableList<Bitmap?> = mutableListOf()
    val Photo: MutableLiveData<CreateOrderPhoto> =  MutableLiveData<CreateOrderPhoto>(CreateOrderPhoto())
//    private val _job: MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }

//    val : LiveData<SearchOrder> = _job
    private val _cleanerbeforePhoto: MutableLiveData<OrderPhotos> by lazy { MutableLiveData<OrderPhotos>() }
    val cleanerbeforePhoto: LiveData<OrderPhotos> by lazy { _cleanerbeforePhoto }
    val photos: MutableList<Bitmap?> = MutableList(3)   { null }

    val order: MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }
    val cleanerPhoto: MutableLiveData<SearchOrderPhotos> by lazy { MutableLiveData<SearchOrderPhotos>() }

//    init {
//        cleanerBeforePhoto()
//    }
//
//    fun cleanerBeforePhoto(){
//        requestTask<SearchOrder>(
//            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/info/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
//            "GET"
//        )?.let {
//            order.value = SearchOrder(
//                photo = it.photo,
//                cleanerId = it.orderId,
//            )
//            _cleanerbeforePhoto.value = OrderPhotos(it.photos as List<ByteArray>)
//        }
//    }



    val capturedCount: Int
        get() = capturedPhotos.size

    fun addCapturedPhoto(photo: Bitmap?) {
        this.Photo.value?.let {
            if (it.photo1 == null) {
                it.photo1 = photo
            } else if (it.photo2 == null) {
                it.photo2 = photo
            } else if (it.photo3 == null) {
                it.photo3 = photo
            }
            this.Photo.value = it
        }
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
