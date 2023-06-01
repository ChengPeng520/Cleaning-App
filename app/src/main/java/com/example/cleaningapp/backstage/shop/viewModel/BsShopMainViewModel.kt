package com.example.cleaningapp.backstage.shop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.Product

class BsShopMainViewModel : ViewModel() {
    private var searchProductList = mutableListOf<Product>()

    // 受監控的LiveData，一旦指派新值就會更新好友列表畫面
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
                if (Product.productname.contains(newText, true)) {
                    searchProductList.add(Product)
                }
            }
            products.value = searchProductList
        }
    }

    /** 模擬取得遠端資料 */
    private fun loadProducts() {
        val productList = mutableListOf<Product>()
        productList.add(Product(R.drawable.product1, "一掃極亮神奇掃把", 100,
                "超好用", 100, "2022-09-09", "2023-09-09"))
        productList.add(Product(R.drawable.product2, "一擦極亮神奇拖把", 100,
            "超好用", 100, "2022-09-09", "2023-09-09"))

                this.searchProductList = productList
                this.products.value = this.searchProductList
    }
}
