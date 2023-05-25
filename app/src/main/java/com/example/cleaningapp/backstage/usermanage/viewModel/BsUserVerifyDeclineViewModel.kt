package com.example.cleaningapp.backstage.usermanage.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BsUserVerifyDeclineViewModel : ViewModel() {
   val editTextValue:MutableLiveData<String> by lazy { MutableLiveData<String>() }
}