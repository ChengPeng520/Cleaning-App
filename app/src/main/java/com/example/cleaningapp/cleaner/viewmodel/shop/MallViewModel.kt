package com.example.cleaningapp.cleaner.viewmodel.shop

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.cleaningapp.cleaner.uistate.ProductItemUiState
import com.example.cleaningapp.cleaner.uistate.ProductUiState
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MallViewModel(application: Application) : AndroidViewModel(application) {
    private var allProductItems = listOf<ProductItemUiState>()
    private val _uiState: MutableStateFlow<ProductUiState> by lazy { MutableStateFlow(ProductUiState()) }
    val uiState: StateFlow<ProductUiState> by lazy { _uiState.asStateFlow() }

    fun fetchProducts() {
        requestTask<List<ProductItemUiState>>(
            path = "product/",
            method = "GET",
            respBodyType = object : TypeToken<List<ProductItemUiState>>() {}.type
        )?.let {
            val filterList: MutableList<ProductItemUiState> = mutableListOf()
            it.forEach { product ->
                if (product.isOnSale) filterList.add(product)
            }
            _uiState.value = ProductUiState(filterList)
            allProductItems = filterList
        }
        fetchShopCart()
    }

    private fun fetchShopCart() {
        requestTask<Int>(
            path = "clShopOrder/fetchCartId/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
            method = "GET"
        )?.let {
            getApplication<Application>().getSharedPreferences(
                "AccountCleaner",
                Context.MODE_PRIVATE
            ).edit()
                .putInt("ShopOrderId", it)
                .apply()
        }
    }

    fun onSearchInputChange(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            _uiState.update {
                it.copy(productItems = allProductItems)
            }
        } else {
            val searchProductItems = mutableListOf<ProductItemUiState>()
            allProductItems.forEach {
                if (it.name.contains(newText, true)) {
                    searchProductItems.add(it)
                }
            }
            _uiState.update {
                it.copy(productItems = searchProductItems)
            }
        }
    }
}