package com.example.cleaningapp.backstage.shop.viewModel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.Product
import com.example.cleaningapp.share.requestTask

class BsShopProductModifyViewModel : ViewModel() {
    val product :MutableLiveData<Product> by lazy { MutableLiveData<Product>(Product()) }
//    val photoBitmap: MutableLiveData<Bitmap?> = MutableLiveData(product.value?.photoBitmap)
//    val isOnSale:MutableLiveData<Boolean?> = MutableLiveData(product.value?.isOnSale)

    fun productModify():Boolean{
        requestTask<Product>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/product/",
            "PUT",
            product.value
        )?.let {
            return true
        }
        return false
    }

    fun  fetchProductInfo(productId:Int){
        requestTask<Product>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/product/$productId",
            "GET"
        )?.let { result ->
            Log.d("productInfo","Received product: $result")
            product.value = result
        }
    }
}