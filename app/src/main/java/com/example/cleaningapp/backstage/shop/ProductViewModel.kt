package com.example.cleaningapp.backstage.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.Product

class ProductViewModel: ViewModel(){
    val product : MutableLiveData<Product> by lazy { MutableLiveData<Product>() }
}