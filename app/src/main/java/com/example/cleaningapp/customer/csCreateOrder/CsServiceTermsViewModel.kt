package com.example.cleaningapp.customer.csCreateOrder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CsServiceTermsViewModel : ViewModel() {
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}