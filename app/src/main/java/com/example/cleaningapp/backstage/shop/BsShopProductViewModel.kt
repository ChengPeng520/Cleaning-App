package com.example.cleaningapp.backstage.shop


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject


open class BsShopProductViewModel : ViewModel() {
    val product: MutableLiveData<Product> by lazy { MutableLiveData<Product>(Product()) }
    //單一商品畫面的viewmodel

    fun productAdd(): Boolean {
        requestTask<JsonObject>(
            "${Constants.BASE_URL}/product/",
            "POST",
            product.value
        )?.let {
            return it.get("result").toString().toBoolean()
        }
        return false
    }
}
