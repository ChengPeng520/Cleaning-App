package com.example.cleaningapp.customer.viewModel

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.detailed.OrderInfo
import com.example.cleaningapp.share.ImageUtils
import com.example.cleaningapp.share.requestTask

class ApplycomplaintViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val orderId: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val returnReason: MutableLiveData<String> by lazy { MutableLiveData<String>() }

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

    fun fetchOrdersInfo(orderId: Int) {
        requestTask<Order>(
            path = "csOrder/$orderId",
            method = "GET",
        )?.let {
            order.value = it
        }
    }

    fun uploadComplaint(view: View) {
        requestTask<OrderInfo>(
            path = "csOrder/",
            method = "PUT",
            reqBody = OrderInfo(
                Order(
                    orderId = order.value!!.orderId,
                    returnReason = returnReason.value!!,
                    cleanerId = order.value!!.cleanerId,
                    commentCleaner = order.value!!.commentCleaner,
                    stars = order.value!!.stars,
                    status = 5
                ), sendPhotos()
            )
        )?.let {
            val bundle = Bundle()
            bundle.putInt("orderId", orderId.value!!)
            view.findNavController().navigate(
                R.id.action_applycomplaintFragment_to_complaintdetailsFragment,
                bundle
            )
        }
    }

    private fun sendPhotos(): List<ByteArray> {
        val photos = mutableListOf<ByteArray>()
        photo1.value?.let {
            photos.add(ImageUtils.bitmapToBytes(it))
        }
        photo2.value?.let {
            photos.add(ImageUtils.bitmapToBytes(it))
        }
        photo3.value?.let {
            photos.add(ImageUtils.bitmapToBytes(it))
        }
        return photos
    }
}