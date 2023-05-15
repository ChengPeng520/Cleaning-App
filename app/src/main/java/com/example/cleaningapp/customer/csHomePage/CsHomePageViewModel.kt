package com.example.cleaningapp.customer.csHomePage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Cleaner

class CsHomePageViewModel : ViewModel() {
    val cleanerList: MutableLiveData<List<Cleaner>> by lazy { MutableLiveData<List<Cleaner>>() }
    private var cleaners = mutableListOf<Cleaner>()

    init {
        fetchCleaners()
    }

    fun fetchCleaners() {
        val list = mutableListOf<Cleaner>()
        list.add(Cleaner(1, R.drawable.cs_cleaner_img1,"曾正凡", true,2.1f, 1))
        list.add(Cleaner(2, R.drawable.cs_cleaner_img2,"呂正芃", true,3.5f, 3))
        list.add(Cleaner(3, R.drawable.cs_cleaner_img3,"王睿睿", true,4.3f, 5))
        list.add(Cleaner(4, R.drawable.cs_cleaner_img4,"掰頂姊", false,5.0f, 7))

        this.cleaners = list
        this.cleanerList.value = this.cleaners
    }
}