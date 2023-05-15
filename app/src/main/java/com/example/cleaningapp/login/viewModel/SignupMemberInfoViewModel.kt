package com.example.cleaningapp.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupMemberInfoViewModel: ViewModel() {
    val gender: MutableLiveData<List<String>>
            by lazy { MutableLiveData<List<String>>(listOf("男", "女")) }
    val avatar: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val fullName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val phone: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val introduction: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}