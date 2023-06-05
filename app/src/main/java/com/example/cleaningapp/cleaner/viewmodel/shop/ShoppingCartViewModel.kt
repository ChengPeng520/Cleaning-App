package com.example.cleaningapp.cleaner.viewmodel.shop

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.ShoppingCartItemUiState
import com.example.cleaningapp.cleaner.uistate.ShoppingCartUiState
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class ShoppingCartViewModel : ViewModel() {
    private val _uiState: MutableLiveData<ShoppingCartUiState> by lazy { MutableLiveData<ShoppingCartUiState>() }
    val uiState: LiveData<ShoppingCartUiState> = _uiState
    val adapterUiState: MutableLiveData<ShoppingCartItemUiState> by lazy { MutableLiveData<ShoppingCartItemUiState>() }
    val list = mutableListOf<ShoppingCartItemUiState>()

    init {
        fetchShopOrderList()
    }

    private fun fetchShopOrderList() {
        requestTask<List<ShoppingCartItemUiState>>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/clShopOrder/nonChecked/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
            method = "GET",
            respBodyType = object : TypeToken<List<ShoppingCartItemUiState>>() {}.type
        )?.let {
            _uiState.value = ShoppingCartUiState(it)
            Log.d("1", it.size.toString())
        }
    }

    fun deleteProduct(productItem: ShoppingCartItemUiState) {
        list.remove(productItem)
    }

    fun updateNumber(position: Int, state: Boolean) {

    }
}