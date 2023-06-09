package com.example.cleaningapp.backstage.shop.viewModel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.Product
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask

class BsShopProductModifyViewModel : ViewModel() {
    val product :MutableLiveData<Product> by lazy { MutableLiveData<Product>(Product()) }


    fun productModify():Boolean{
        requestTask<Product>(
            "${Constants.BASE_URL}/product/",
            "PUT",
            product.value
        )?.let {
            return true
        }
        return false
    }

    fun  fetchProductInfo(productId:Int){
        requestTask<Product>(
            "${Constants.BASE_URL}/product/$productId",
            "GET"
        )?.let { result ->
            Log.d("productInfo","Received product: $result")
            product.value = result
        }
    }
}