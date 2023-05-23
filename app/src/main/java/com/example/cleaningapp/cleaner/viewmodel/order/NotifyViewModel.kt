package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.NotifyItemUiState
import com.example.cleaningapp.cleaner.uistate.NotifyUiState

class NotifyViewModel : ViewModel() {
    private val _uiState: MutableLiveData<NotifyUiState> by lazy { MutableLiveData<NotifyUiState>() }
    val uiState: LiveData<NotifyUiState> by lazy { _uiState }

    fun fetchNotify() {
        val notifyList = mutableListOf<NotifyItemUiState>()
        notifyList.add(NotifyItemUiState(1, "客訴通知", "2023/04/23"))
        notifyList.add(NotifyItemUiState(2, "客訴通知", "2023/04/23"))
        notifyList.add(NotifyItemUiState(3, "訂單成立通知", "2023/04/23"))
        _uiState.value = NotifyUiState(notifyList)
    }
}