package com.example.cleaningapp.cleaner.viewmodel.member

import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.example.cleaningapp.cleaner.uistate.CleanerMemberInfoUiState
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask

class CleanerMemberInfoViewModel : ViewModel() {
    val uiState: MutableLiveData<CleanerMemberInfoUiState> by lazy {
        MutableLiveData<CleanerMemberInfoUiState>(
            CleanerMemberInfoUiState()
        )
    }

    init {
        val result = requestTask<CleanerSharedPreferencesUtils.ApiCleanerModel>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/AccountCustomer/kk0128k@gmail.com/kk0128k"
        )
        if (result != null) {
            CleanerSharedPreferencesUtils.saveCleanerInfoFromPreferences(result)
            uiState.value =
                CleanerSharedPreferencesUtils.fetchCleanerInfoFromPreferences<CleanerMemberInfoUiState>()
        }
    }

    fun saveMemberInfo(view: View) {
        if (uiState.value?.name?.isNotEmpty() == true && uiState.value?.identifyNumber?.isNotEmpty() == true && uiState.value?.phone?.isNotEmpty() == true) {
            val uiState = CleanerSharedPreferencesUtils.anyToApiCleanerModel(uiState.value!!)
            val result = requestTask<CleanerSharedPreferencesUtils.ApiCleanerModel>(
                "http://10.0.2.2:8080/javaweb-cleaningapp/AccountCustomer",
                "POST",
                uiState
            )
            if (result != null) {
                if (CleanerSharedPreferencesUtils.saveCleanerInfoFromPreferences(result))
                    Toast.makeText(view.context, "儲存成功", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(view.context, "儲存失敗", Toast.LENGTH_SHORT).show()
            }
        }
    }
}