package com.example.cleaningapp.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupContractViewModel : ViewModel() {
    val radioId: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val contract: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}