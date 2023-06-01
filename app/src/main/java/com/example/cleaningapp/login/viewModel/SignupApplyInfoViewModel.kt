package com.example.cleaningapp.login.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask

private data class Cleaner(
    val email: String?,
    val password: String? = null,
    val name: String?,
    val photo: Bitmap?,
    val phone: String?,
    val gender: Int,
    val introduction: String?,
    val identifyNumber: String?,
    val idCardFront: Bitmap?,
    val idCardBack: Bitmap?,
    val crc: Bitmap?
)

class SignupApplyInfoViewModel : ViewModel() {
    var email: String? = null
    var password: String? = null
    val gender: MutableLiveData<Int> by lazy { MutableLiveData<Int>(0) }
    val fullName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val phone: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val introduction: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val identifyNumber: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val avatar: MutableLiveData<Bitmap> by lazy { MutableLiveData<Bitmap>(null) }
    val id1: MutableLiveData<Bitmap> by lazy { MutableLiveData<Bitmap>(null) }
    val id2: MutableLiveData<Bitmap> by lazy { MutableLiveData<Bitmap>(null) }
    val crc: MutableLiveData<Bitmap> by lazy { MutableLiveData<Bitmap>(null) }

    fun register(): Boolean? {
        val member =
            Cleaner(
                email = email,
                password = password,
                name = fullName.value,
                photo = avatar.value,
                phone = phone.value,
                gender = gender.value!!,
                introduction = introduction.value,
                identifyNumber = identifyNumber.value,
                idCardFront = id1.value,
                idCardBack = id2.value,
                crc = crc.value
            )
        val apiCleanerModel = CleanerSharedPreferencesUtils.anyToApiCleanerModel(member)
        return requestTask<Boolean>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/AccountCleaner/",
            "POST",
            apiCleanerModel
        )
    }
}