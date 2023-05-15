package com.example.cleaningapp.backstage.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.Product

open  class BsShopProductViewModel : ViewModel() {
    val product:MutableLiveData<Product> by lazy { MutableLiveData<Product>() }


}

//單一商品畫面的viewmodel