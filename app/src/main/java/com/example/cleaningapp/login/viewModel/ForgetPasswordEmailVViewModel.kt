package com.example.cleaningapp.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForgetPasswordEmailVViewModel : ViewModel() {
    val emailVerify: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}