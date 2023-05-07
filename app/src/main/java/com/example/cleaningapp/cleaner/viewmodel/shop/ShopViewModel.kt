package com.example.cleaningapp.cleaner.viewmodel.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.ProductsItemUiState
import com.example.cleaningapp.cleaner.uistate.ProductsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShopViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState = _uiState.asStateFlow()
    val itemUiState = MutableLiveData<ProductsItemUiState>()

    fun fetchProducts() {
        val products = mutableListOf<ProductsItemUiState>()
        products.add(ProductsItemUiState(1, R.drawable.fatruei_test1, "掃把", 100))
        products.add(ProductsItemUiState(2, R.drawable.fatruei_test2,"抹布", 100))
        products.add(ProductsItemUiState(3, R.drawable.fatruei_test3,"拖把組", 100))
        products.add(ProductsItemUiState(4, R.drawable.fatruei_test4,"夾子", 50))
        _uiState.update {
            it.copy(products = products)
        }
    }
}