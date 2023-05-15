package com.example.cleaningapp.customer.recyclerView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Cleaner

class CsCleanerViewModel: ViewModel() {
    val cleanerItem: MutableLiveData<Cleaner> by lazy { MutableLiveData<Cleaner>() }

}