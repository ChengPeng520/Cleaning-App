package com.example.cleaningapp.backstage.shop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.Product

class BsShopProductViewModel : ViewModel() {
    val product: MutableLiveData<Product> by lazy { MutableLiveData<Product>() }
}