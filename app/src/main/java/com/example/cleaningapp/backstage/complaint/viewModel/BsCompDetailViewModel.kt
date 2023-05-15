package com.example.cleaningapp.backstage.complaint.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.complaint.model.Complaint

class BsCompDetailViewModel : ViewModel() {
    val complaint: MutableLiveData<Complaint> by lazy { MutableLiveData<Complaint>() }
}