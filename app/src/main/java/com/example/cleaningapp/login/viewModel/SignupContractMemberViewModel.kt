package com.example.cleaningapp.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupContractMemberViewModel : ViewModel() {
    val contractMember: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}