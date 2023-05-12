package com.example.cleaningapp.cleaner.viewmodel.shop

import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.ProductItemUiState
import com.example.cleaningapp.cleaner.uistate.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MallViewModel : ViewModel() {
    private var allProductItems = listOf<ProductItemUiState>()
    private val _uiState: MutableStateFlow<ProductUiState> by lazy { MutableStateFlow(ProductUiState()) }
    val uiState: StateFlow<ProductUiState> by lazy { _uiState.asStateFlow() }

    fun fetchProducts() {
        val productItems = mutableListOf<ProductItemUiState>()
        productItems.add(ProductItemUiState(1, R.drawable.fatruei_test1, "掃把", 100))
        productItems.add(ProductItemUiState(2, R.drawable.fatruei_test2, "抹布", 100))
        productItems.add(ProductItemUiState(3, R.drawable.fatruei_test3, "拖把組", 100))
        productItems.add(ProductItemUiState(4, R.drawable.fatruei_test4, "夾子", 50))
        _uiState.update {
            it.copy(productItems = productItems)
        }
        allProductItems = productItems
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