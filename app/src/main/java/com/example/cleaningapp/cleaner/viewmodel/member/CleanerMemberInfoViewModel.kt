package com.example.cleaningapp.cleaner.viewmodel.member

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CleanerMemberInfoViewModel : ViewModel() {
    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val gender: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val identity: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val phone: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val introduction: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}