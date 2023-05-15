package com.example.cleaningapp.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResetPasswordViewModel : ViewModel() {
    val password: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val cPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}