package com.example.cleaningapp.backstage.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.Product

class BsShopMainViewModel : ViewModel() {
    private var productList = mutableListOf<Product>()
    val products: MutableLiveData<List<Product>> by lazy { MutableLiveData<List<Product>>() }

    init {
        loadProducts()
    }

    private fun loadProducts() {
        val productList = mutableListOf<Product>()
        productList.add(Product(R.drawable.product1, "掃把", 500,
                "十分好用十分好用十分好用十分好用", 100,  "2020-05-10", "2020-05-11"
            )
        )
        productList.add(
            Product(R.drawable.product2,"拖把", 200,
                "十分好用十分好用十分好用十分好用", 100,  "2020-05-10", "2020-05-11"
            )
        )
        this.productList = productList
        this.products.value =this.productList

    }

    fun productSearch(newText: String) {
        if (newText.isEmpty()) {
            products.value = productList
        } else {
            val searchProduct = mutableListOf<Product>()
            productList.forEach { product: Product ->
                if (product.productname.contains(
                        newText,
                        true
                    )
                ) searchProduct.add(product)
            }
            products.value = searchProduct
        }

    }
}