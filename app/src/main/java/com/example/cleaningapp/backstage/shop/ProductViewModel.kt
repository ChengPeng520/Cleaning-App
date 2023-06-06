package com.example.cleaningapp.backstage.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.shop.Product
/**
 * 單一筆商品資料處理
 */
class ProductViewModel: ViewModel(){
    val product : MutableLiveData<Product> by lazy { MutableLiveData<Product>() }
}