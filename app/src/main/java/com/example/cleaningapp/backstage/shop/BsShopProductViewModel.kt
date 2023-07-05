package com.example.cleaningapp.backstage.shop


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import java.lang.reflect.GenericArrayType


open class BsShopProductViewModel : ViewModel() {
    val product: MutableLiveData<Product> by lazy { MutableLiveData<Product>(Product()) }
    //單一商品畫面的viewmodel

    fun productAdd(): Boolean {
        requestTask<JsonObject>(
            "product/*",
            "POST",
            product.value
        )?.let {
            return it.get("result").toString().toBoolean()
        }
        return false
    }
}
