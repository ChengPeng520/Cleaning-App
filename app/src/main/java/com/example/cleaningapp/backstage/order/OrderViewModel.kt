package com.example.cleaningapp.backstage.order

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.ImageUtils
import com.example.cleaningapp.share.requestTask


/**
 * 單一訂單資料處理
 */
class OrderViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>(Order()) }
    val orderPhotos: MutableLiveData<com.example.cleaningapp.backstage.order.OrderPhotos> by lazy { MutableLiveData<com.example.cleaningapp.backstage.order.OrderPhotos>() }
    val cleaningPhotos: MutableLiveData<CleaningPhotos> by lazy { MutableLiveData<CleaningPhotos>() }

    fun fetchBackstageOrderInfo(orderId : Int) {
        requestTask<BackstageOrderInfo>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/bsOrder/1",
            "GET"
        )?.let { backstageorderInfo ->
            order.value = backstageorderInfo.order
            val cleaningPhotosBitmap = backstageorderInfo.cleaningPhotos.map {
                ImageUtils.bytesToBitmap(it)
            }
            cleaningPhotos.value = CleaningPhotos(cleaningPhotosBitmap)
            val orderPhotosBitmap = backstageorderInfo.orderPhotos.map {
                ImageUtils.bytesToBitmap(it)
            }
            orderPhotos.value = OrderPhotos(orderPhotosBitmap)



            Log.d("TAG", "接收內容:$backstageorderInfo")
        }
    }
}