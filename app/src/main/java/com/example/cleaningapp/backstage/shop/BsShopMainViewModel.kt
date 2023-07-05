package com.example.cleaningapp.backstage.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class BsShopMainViewModel : ViewModel() {
    var productList = listOf<Product>()
    val products: MutableLiveData<List<Product>> by lazy { MutableLiveData<List<Product>>() }

    init {
        loadProducts()
    }

    fun loadProducts() {
        requestTask<List<Product>>(
            "product/",
            "GET",
            respBodyType = object : TypeToken<List<Product>>() {}.type
        )?.let { response ->
            products.value = response
            productList = response
        }
    }

    fun productSearch(newText: String) {
        if (newText.isEmpty()) {
            products.value = productList
        } else {
            val searchProduct = mutableListOf<Product>()
            productList.forEach { product: Product ->
                if (product.name?.contains(
                        newText,
                        true
                    ) == true
                ) searchProduct.add(product)
            }
            products.value = searchProduct
        }
    }
}