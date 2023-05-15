package com.example.cleaningapp.backstage.usermanage.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.usermanage.model.User

class BsUserMainModifyViewModel : ViewModel() {
    val user: MutableLiveData<User> by lazy { MutableLiveData<User>() }

}