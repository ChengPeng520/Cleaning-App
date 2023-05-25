package com.example.cleaningapp.cleaner.viewmodel.member

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.example.cleaningapp.cleaner.uistate.MemberInfoUiState
import com.example.cleaningapp.data.IMemberRepository
import kotlinx.coroutines.launch

class CleanerMemberInfoViewModel(
    private val application: Application,
    private val IMemberRepository: IMemberRepository
) :
    AndroidViewModel(application) {
    val uiState: MutableLiveData<MemberInfoUiState> by lazy { MutableLiveData<MemberInfoUiState>() }

    init {
        fetchMemberInfo()
    }

    private fun fetchMemberInfo() {
        viewModelScope.launch {
            try {
                val memberInfo = IMemberRepository.fetchMemberInfo(application = application)
                uiState.value = memberInfo
            } catch (ioe: Exception) {
                ioe.printStackTrace()
            }
        }
    }

    fun saveSharePreferences(view: View) {
        if (uiState.value != null && uiState.value!!.name.isNotEmpty() && uiState.value!!.identityNumber.isNotEmpty() && uiState.value!!.phone.isNotEmpty()) {
            val memberInfo = MemberInfoUiState(
                uiState.value!!.name,
                uiState.value!!.gender,
                uiState.value!!.identityNumber,
                uiState.value!!.phone,
                uiState.value!!.introduction
            )

            viewModelScope.launch {
                val resultText = IMemberRepository.saveMemberInfo(application, memberInfo)
                Toast.makeText(view.context, resultText, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun provideFactory(
            application: Application,
            IMemberRepository: IMemberRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CleanerMemberInfoViewModel(application, IMemberRepository) as T
                }
            }
    }
}