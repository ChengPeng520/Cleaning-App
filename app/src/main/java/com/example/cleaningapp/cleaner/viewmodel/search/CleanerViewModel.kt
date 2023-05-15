package com.example.cleaningapp.cleaner.viewmodel.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.Job

class CleanerViewModel : ViewModel(){
    val cleaner :  MutableLiveData<Job> by lazy { MutableLiveData<Job>() }

}
//livedata 為了連結 databinding