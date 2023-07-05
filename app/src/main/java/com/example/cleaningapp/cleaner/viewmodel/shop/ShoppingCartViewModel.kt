package com.example.cleaningapp.cleaner.viewmodel.shop

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.ShopOrder
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
            path = "clShopOrder/nonChecked/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
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
            path = "ShopOrderList/${productItem.shopOrderId}/${productItem.productId}",
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
                path = "ShopOrderList/",
                method = "PUT",
                reqBody = productItem
            )?.let {
                fetchShopOrderList()
            }
        } else {
            productItem.count -= 1
            requestTask<ShoppingCartItemUiState>(
                path = "ShopOrderList/",
                method = "PUT",
                reqBody = productItem
            )?.let {
                fetchShopOrderList()
            }
        }
    }

    fun checkout(context: Context) : Boolean {
        requestTask<ShopOrder>(
            path = "clShopOrder/",
            method = "PUT",
            reqBody = ShopOrder(
                shopOrderId = context.getSharedPreferences(
                    "AccountCleaner",
                    Context.MODE_PRIVATE
                )
                    .getInt("ShopOrderId", 0),
                recieverName = context.getSharedPreferences(
                    "ReceiverInfo",
                    Context.MODE_PRIVATE
                ).getString("name", "")!!,
                recieverPhone = context.getSharedPreferences(
                    "ReceiverInfo",
                    Context.MODE_PRIVATE
                ).getString("phone", "")!!,
                recieverAddress = context.getSharedPreferences(
                    "ReceiverInfo",
                    Context.MODE_PRIVATE
                ).getString("address", "")!!,
                totalPrice = totalPrice.value!!
            )
        )?.let {
            return true
        }
        return false
    }
}