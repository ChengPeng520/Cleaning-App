package com.example.cleaningapp.cleaner.viewmodel.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.ShoppingCartItemUiState
import com.example.cleaningapp.cleaner.uistate.ShoppingCartUiState

class ShoppingCartViewModel : ViewModel() {
    private val _uiState: MutableLiveData<ShoppingCartUiState> by lazy { MutableLiveData<ShoppingCartUiState>() }
    val uiState: LiveData<ShoppingCartUiState> = _uiState
    val adapterUiState: MutableLiveData<ShoppingCartItemUiState> by lazy { MutableLiveData<ShoppingCartItemUiState>() }

    fun fetchCleanerShoppingCartInfo() {
        val list = mutableListOf<ShoppingCartItemUiState>()
        list.add(ShoppingCartItemUiState(1, R.drawable.fatruei_test1, "掃把", 100, 1))
        list.add(ShoppingCartItemUiState(2, R.drawable.fatruei_test3, "拖把組", 100, 1))
        _uiState.value = ShoppingCartUiState(shoppingCartItems = list, grossPrice = 200)
    }
}