package com.example.cleaningapp.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Member(val username: String, val password: String)
class SignupViewModel : ViewModel() {
    val status: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>(
            listOf(
                "一般會員",
                "清潔人員"
            )
        )
    }
    val account: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val password: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val cPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun signup() {
//        val member = Member(username = account.value!!, password = password.value!!)
//        val result = requestTask<Boolean>(
//            "http://10.0.2.2:8080/javaweb-exercise-09/member",
//            "POST",
//            member
//        )
//        println(result)
    }
}