package com.example.cleaningapp.backstage.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R

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
        productList.add(Product(R.drawable.fatruei_test1, "一擦極亮神奇抹布", "100"))
        productList.add(Product(R.drawable.fatruei_test1, "超強絕對不掉的夾子", "100"))
        productList.add(Product(R.drawable.fatruei_test1, "一擦極亮神奇抹布", "100"))
        productList.add(Product(R.drawable.fatruei_test1, "超強絕對不掉的夾子", "100"))

        this.searchProductList = productList
        this.products.value = this.searchProductList
    }
}
