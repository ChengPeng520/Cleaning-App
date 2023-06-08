package com.example.cleaningapp.cleaner.viewmodel.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.OrderStateUiState
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.cleaner.uistate.Work
import com.example.cleaningapp.share.OrderUtil
import com.example.cleaningapp.share.requestTask

class OrdersViewModel: ViewModel(){
    val order : MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }
}