package com.example.cleaningapp.cleaner.viewmodel.member

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CleanerMemberInfoViewModel(private val application: Application) :
    AndroidViewModel(application) {
    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val gender: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val identityNumber: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val phone: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val introduction: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        val preferences = application.getSharedPreferences("AccountCleaner", Context.MODE_PRIVATE)
        name.value = preferences.getString("name", "")
        gender.value = preferences.getInt("gender", 0)
        identityNumber.value = preferences.getString("identityNumber", "")
        phone.value = preferences.getString("phone", "")
        introduction.value = preferences.getString("introduction", "")
    }

    fun saveSharePreferences(view: View) {
        application.getSharedPreferences("AccountCleaner", Context.MODE_PRIVATE).edit()
            .putString("name", name.value)
            .putInt("gender", gender.value!!)
            .putString("identityNumber", identityNumber.value)
            .putString("phone", phone.value)
            .putString("introduction", introduction.value)
            .apply()
        Toast.makeText(view.context, "個人資料已儲存", Toast.LENGTH_SHORT).show()
    }
}