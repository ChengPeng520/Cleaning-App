package com.example.cleaningapp.backstage.shop.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.coupon.Coupon
import com.example.cleaningapp.backstage.shop.Product
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class BsShopMainViewModel : ViewModel() {

    private var searchProductList = mutableListOf<Product>()

    // 受監控的LiveData，一旦指派新值就會更新列表畫面
    val products: MutableLiveData<List<Product>> by lazy { MutableLiveData<List<Product>>() }

    init {
        loadProducts()
    }


    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            products.value = searchProductList
        } else {
            val searchProductList = mutableListOf<Product>()
            searchProductList.forEach { Product ->
                if (Product.name?.contains(newText, true) == true) {
                    searchProductList.add(Product)
                }
            }
            products.value = searchProductList
        }
    }

    /** 模擬取得遠端資料 */
    private fun loadProducts() {
        requestTask<List<Product>>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/product/",
            "GET",
            respBodyType = object : TypeToken<List<Product>>() {}.type
        )?.let {
            products.value =it
        }
        val productList = mutableListOf<Product>()
        this.searchProductList = productList
        this.products.value = this.searchProductList
    }
}
