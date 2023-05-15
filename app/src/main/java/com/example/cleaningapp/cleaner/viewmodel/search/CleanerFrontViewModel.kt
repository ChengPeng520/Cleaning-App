package com.example.cleaningapp.cleaner.viewmodel.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.Job

class CleanerFrontViewModel : ViewModel() {

    // 原始Cleaner列表
    private var cleanerList = mutableListOf<Job>()

    // 受監控的LiveData，一旦指派新值就會更新Cleaner列表畫面
    val cleaner: MutableLiveData<List<Job>> by lazy { MutableLiveData<List<Job>>()}

    init {
        loadCleaners()
    }

    /** 模擬取得遠端資料 */
    private fun loadCleaners() {
        val cleanerList = mutableListOf<Job>()
        cleanerList.add(Job(R.drawable.alb_account_avatar, "Ivy", "2023-03-22", "台北", "4000元整"))
        cleanerList.add(Job(R.drawable.alb_account_avatar, "Mary", "2023-02-22", "台南", "31111元整"))
        cleanerList.add(Job(R.drawable.alb_account_avatar, "Sue", "2023-03-23", "台中", "1231元整"))
        this.cleanerList = cleanerList
        this.cleaner.value = this.cleanerList
    }
    val county: MutableLiveData<List<String>>
            by lazy { MutableLiveData<List<String>>(listOf("臺北市",
                    "新北市" ,
                    "桃園市" ,
                    "臺中市" ,
                    "臺南市" ,
                    "高雄市" ,
                    "新竹縣" ,
                    "苗栗縣" ,
                    "彰化縣" ,
                    "南投縣" ,
                    "雲林縣" ,
                    "嘉義縣" ,
                    "屏東縣" ,
                    "宜蘭縣" ,
                    "花蓮縣" ,
                    "臺東縣" ,
                    "澎湖縣" ,
                    "金門縣" ,
                    "連江縣" ,
                    "基隆市" ,
                    "新竹市" ,
                    "嘉義市")) }
    val text:MutableLiveData<String> by lazy { MutableLiveData<String>()}
}
//by lazy 晚一點啟用
//MutableLiveData<List<Job>>() 括弧為初始質
