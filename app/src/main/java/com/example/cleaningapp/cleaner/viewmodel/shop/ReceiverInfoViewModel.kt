package com.example.cleaningapp.cleaner.viewmodel.shop

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceiverInfoViewModel : ViewModel() {
    val receiverName = MutableLiveData<String>()
    val receiverTelPhone = MutableLiveData<String>()
    val receiverAddress = MutableLiveData<String>()

    fun onBtnClick(view: View) {

    }
}