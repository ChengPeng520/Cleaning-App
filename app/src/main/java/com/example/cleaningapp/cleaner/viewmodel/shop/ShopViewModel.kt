package com.example.cleaningapp.cleaner.viewmodel.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.ProductItemUiState
import com.example.cleaningapp.cleaner.uistate.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShopViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState = _uiState.asStateFlow()
    val itemUiState = MutableLiveData<ProductItemUiState>()

    fun fetchProducts() {
        val products = mutableListOf<ProductItemUiState>()
        products.add(ProductItemUiState(1, R.drawable.fatruei_test1, "掃把", 100))
        products.add(ProductItemUiState(2, R.drawable.fatruei_test2,"抹布", 100))
        products.add(ProductItemUiState(3, R.drawable.fatruei_test3,"拖把組", 100))
        products.add(ProductItemUiState(4, R.drawable.fatruei_test4,"夾子", 50))
        _uiState.update {
            it.copy(products = products)
        }
    }

    fun onSearchInputChange() {

    }
}