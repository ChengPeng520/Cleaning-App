package com.example.cleaningapp.cleaner.viewmodel.shop

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.ProductDetailUiState
import com.example.cleaningapp.cleaner.uistate.ShopOrderList
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState = _uiState.asStateFlow()
    val shopOrderId: Int = application.getSharedPreferences("AccountCleaner", Context.MODE_PRIVATE)
        .getInt("ShopOrderId", 0)

    fun fetchProductDetail(productId: Int) {
        requestTask<ProductDetailUiState>(
            url = "${Constants.BASE_URL}/product/$productId",
            method = "GET"
        )?.let {
            _uiState.value = it
        }
    }

    fun updateCount(view: View) {
        val oldCount = uiState.value.count
        when (view.id) {
            R.id.btn_product_detail_plus -> {
                val newCount = uiState.value.count + 1
                _uiState.update {
                    it.copy(
                        count = newCount
                    )
                }
            }
            R.id.btn_product_detail_minus -> {
                if (oldCount != 0) {
                    val newCount = uiState.value.count - 1
                    _uiState.update {
                        it.copy(
                            count = newCount
                        )
                    }
                }
            }
        }
    }

    fun putProductToCart(view: View) {
        if (uiState.value.count > 0) {
            requestTask<JsonObject>(
                url = "${Constants.BASE_URL}/ShopOrderList/",
                method = "POST",
                reqBody = ShopOrderList(
                    shopOrderId = shopOrderId,
                    productId = uiState.value.productId,
                    count = uiState.value.count,
                    price = uiState.value.totalPrice
                )
            )?.let {
                if (it.get("result").asBoolean) {
                    Toast.makeText(view.context, "已將商品儲存至購物車內", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(view.context, "儲存失敗", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}