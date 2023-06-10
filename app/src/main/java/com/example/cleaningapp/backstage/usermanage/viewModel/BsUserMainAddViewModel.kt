package com.example.cleaningapp.backstage.usermanage.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.BackstageSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject

class BsUserMainAddViewModel : ViewModel() {
    val account: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val password: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    var suspend: Boolean = false
    val fullName: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun backstageRegister(): Boolean {
        val backstage = AccountBackstage(
            account = account.value,
            password = password.value,
            name = fullName.value,
            suspend = suspend
        )
        val apiBackstageModel = BackstageSharedPreferencesUtils.anyToApiBackstageModel(backstage)
        requestTask<JsonObject>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/",
            method = "POST",
            reqBody = apiBackstageModel
        )?.let {
            return it.get("result").toString().toBoolean()
        }
        return false
    }
}

private data class AccountBackstage(
    val account: String?,
    val password: String? = null,
    val name: String?,
    val suspend: Boolean = false,
)