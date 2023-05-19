package com.example.cleaningapp.cleaner.viewmodel.shop

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.ProductDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductDetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchProductDetail(productId: Int) {
        _uiState.update {
            it.copy(
                id = productId,
                image = R.drawable.fatruei_test1,
                name = "掃把",
                description = "這是飛天掃把",
                price = 50,
                totalPrice = 50 * uiState.value.count
            )
        }
    }

    fun updateCount(view: View) {
        val oldCount = uiState.value.count
        when (view.id) {
            R.id.btn_product_detail_plus -> {
                val newCount = uiState.value.count + 1
                _uiState.update {
                    it.copy(
                        count = newCount,
                        totalPrice = uiState.value.price * newCount
                    )
                }
            }
            R.id.btn_product_detail_minus -> {
                if (oldCount != 0) {
                    val newCount = uiState.value.count - 1
                    _uiState.update {
                        it.copy(
                            count = newCount,
                            totalPrice = uiState.value.price * newCount
                        )
                    }
                }
            }
        }
    }

    fun putProductToCart(view: View) {
        if (uiState.value.count > 0) {
            Toast.makeText(view.context, "已將商品儲存至購物車內", Toast.LENGTH_SHORT).show()
        }
    }
}