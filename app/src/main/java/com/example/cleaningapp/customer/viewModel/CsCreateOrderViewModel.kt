package com.example.cleaningapp.customer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CsCreateOrderViewModel : ViewModel() {
//    預設___坪
    val livingroomSize: MutableLiveData<String> by lazy {MutableLiveData<String>("坪")}
}