package com.example.cleaningapp.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    val status: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>(
            listOf(
                "一般會員",
                "清潔人員"
            )
        )
    }
    val account: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val password: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val cPassword: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
}