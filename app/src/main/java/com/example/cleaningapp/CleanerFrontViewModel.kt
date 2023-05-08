package com.example.cleaningapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CleanerFrontViewModel: ViewModel(){
    // 原始Cleaner列表
    private var cleanerList = mutableListOf<CleanerFrontModel>()

    // 受監控的LiveData，一旦指派新值就會更新Cleaner列表畫面
    val cleaner: MutableLiveData<List<CleanerFrontModel>> by lazy { MutableLiveData<List<CleanerFrontModel>>() }

}


