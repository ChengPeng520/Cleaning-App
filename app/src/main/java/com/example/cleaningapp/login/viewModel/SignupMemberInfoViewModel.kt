package com.example.cleaningapp.login.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject

private data class Customer(
    val email: String?,
    val password: String? = null,
    val name: String?,
    val photo: Bitmap?,
    val phone: String?,
    val gender: Int,
    val introduction: String?
)

class SignupMemberInfoViewModel : ViewModel() {
    var email: String? = null
    var password: String? = null
    val gender: MutableLiveData<Int>
            by lazy { MutableLiveData<Int>(0) }
    val fullName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val phone: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val introduction: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val avatar: MutableLiveData<Bitmap> by lazy { MutableLiveData<Bitmap>(null) }

    fun customerRegister(): Boolean {
        val customer =
            Customer(
                email = email,
                password = password,
                name = fullName.value,
                photo = avatar.value,
                phone = phone.value,
                gender = gender.value!!,
                introduction = introduction.value,
            )
        val apiCustomerModel = CustomerSharePreferencesUtils.anyToApiCustomerModel(customer)
        requestTask<JsonObject>(
            "AccountCustomer/", "POST", apiCustomerModel
        )?.let {
            return it.get("result").toString().toBoolean()
        }
        return false
    }
}