package com.example.cleaningapp.cleaner.viewmodel.shop

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation

class ReceiverInfoViewModel(private val application: Application) : AndroidViewModel(application) {
    val receiverName = MutableLiveData<String>()
    val receiverTelPhone = MutableLiveData<String>()
    val receiverAddress = MutableLiveData<String>()

    init {
        val preferences = application.getSharedPreferences("AccountCleaner", Context.MODE_PRIVATE)
        receiverName.value = preferences.getString("name", "")
        receiverTelPhone.value = preferences.getString("phone", "")
        receiverAddress.value = preferences.getString("address", "")
    }

    fun saveSharePreferences(view: View) {
        application.getSharedPreferences("AccountCleaner", Context.MODE_PRIVATE).edit()
            .putString("name", receiverName.value)
            .putString("phone", receiverTelPhone.value)
            .putString("address", receiverAddress.value)
            .apply()
        Toast.makeText(view.context, "已儲存配送資訊", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }
}