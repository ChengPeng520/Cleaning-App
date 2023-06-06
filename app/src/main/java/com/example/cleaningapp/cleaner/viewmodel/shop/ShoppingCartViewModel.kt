package com.example.cleaningapp.cleaner.viewmodel.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.ShoppingCartItemUiState
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class ShoppingCartViewModel : ViewModel() {
    private val _uiState: MutableLiveData<List<ShoppingCartItemUiState>> by lazy { MutableLiveData<List<ShoppingCartItemUiState>>() }
    val uiState: LiveData<List<ShoppingCartItemUiState>> = _uiState
    val adapterUiState: MutableLiveData<ShoppingCartItemUiState> by lazy { MutableLiveData<ShoppingCartItemUiState>() }
    val totalPrice: MutableLiveData<Int> by lazy { MutableLiveData(0) }

    init {
        fetchShopOrderList()
    }

    fun fetchShopOrderList() {
        requestTask<List<ShoppingCartItemUiState>>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/clShopOrder/nonChecked/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
            method = "GET",
            respBodyType = object : TypeToken<List<ShoppingCartItemUiState>>() {}.type
        )?.let {
            _uiState.value = it
            var price = 0
            for (i in it.indices) {
                price += it[i].price * it[i].count
            }
            totalPrice.value = price
        }
    }

    fun deleteProduct(productItem: ShoppingCartItemUiState): Boolean {
        requestTask<JsonObject>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/ShopOrderList/${productItem.shopOrderId}/${productItem.productId}",
            method = "DELETE",
        )?.let {
            return it.get("result").asBoolean
        }
        return false
    }

    fun updateNumber(productItem: ShoppingCartItemUiState, state: Boolean) {
        if (state) {
            productItem.count += 1
            requestTask<ShoppingCartItemUiState>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/ShopOrderList/",
                method = "PUT",
                reqBody = productItem
            )?.let {
                fetchShopOrderList()
            }
        } else {
            productItem.count -= 1
            requestTask<ShoppingCartItemUiState>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/ShopOrderList/",
                method = "PUT",
                reqBody = productItem
            )?.let {
                fetchShopOrderList()
            }
        }
    }
}