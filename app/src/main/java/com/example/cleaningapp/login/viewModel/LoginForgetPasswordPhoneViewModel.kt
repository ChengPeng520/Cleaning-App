package com.example.cleaningapp.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginForgetPasswordPhoneViewModel : ViewModel() {
    val phone: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}