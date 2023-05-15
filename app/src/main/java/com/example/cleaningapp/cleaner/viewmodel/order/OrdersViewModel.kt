package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.Work

class OrdersViewModel: ViewModel(){
    val order : MutableLiveData<Work> by lazy { MutableLiveData<Work>() }
}