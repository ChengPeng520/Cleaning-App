package com.example.cleaningapp.cleaner.viewmodel.shop

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation

class ReceiverInfoViewModel : ViewModel() {
    val receiverName = MutableLiveData<String>()
    val receiverTelPhone = MutableLiveData<String>()
    val receiverAddress = MutableLiveData<String>()

    fun onBtnClick(view: View) {
        Navigation.findNavController(view).popBackStack()
    }
}