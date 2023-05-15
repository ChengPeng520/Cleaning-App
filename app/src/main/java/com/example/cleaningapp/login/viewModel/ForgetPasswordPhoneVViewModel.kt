package com.example.cleaningapp.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForgetPasswordPhoneVViewModel : ViewModel() {
    val phone: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}