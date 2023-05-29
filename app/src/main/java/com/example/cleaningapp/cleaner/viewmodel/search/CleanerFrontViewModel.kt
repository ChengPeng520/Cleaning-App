package com.example.cleaningapp.cleaner.viewmodel.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.Job

class CleanerFrontViewModel : ViewModel() {

    // 原始Cleaner列表
    private var cleanerList = mutableListOf<Job>()

    // 受監控的LiveData，一旦指派新值就會更新Cleaner列表畫面
    val cleaner: MutableLiveData<List<Job>> by lazy { MutableLiveData<List<Job>>() }

    init {
        loadCleaners()
    }

    /** 模擬取得遠端資料 */
    private fun loadCleaners() {
        val cleanerList = mutableListOf<Job>()
        cleanerList.add(Job(R.drawable.alb_account_avatar, "曾正凡", "1111111",
            "台北", "中山區","台北市中山區","臺北市中山區南京東路三段219號5樓","2023年4月26日",
            "12:00-14:00","12:00","14:00",
            5.0,5.0,5.0,5.0,
            "工具:  吸塵器 、拖把\n" +
                    "整理重點:  臥房、客房 ", null, null,null,"1111元整", 0 ))

        cleanerList.add(Job(R.drawable.alb_account_avatar,  "王濬濬", "22222222",
            "台北", "中山區","台北市中山區","臺北市中山區南京東路三段219號6樓","2023年4月27日",
            "13:00-15:00","13:00","15:00",
            5.0,5.0,5.0,5.0,
            "工具:  吸塵器 、拖把\n" +
                    "整理重點:  臥房、客房 ", null, null,null,"2222元整", 0 ))

        cleanerList.add(Job(R.drawable.alb_account_avatar,  "掰頂姊", "33333333",
            "台北", "中山區","台北市中山區","臺北市中山區南京東路三段219號7樓","2023年4月28日",
            "14:00-16:00","14:00","16:00",
            5.0,5.0,5.0,5.0,
            "工具:  吸塵器 、拖把\n" +
                    "整理重點:  臥房、客房 ", null,null, null,"3333元整", 0 ))

        this.cleanerList = cleanerList
        this.cleaner.value = this.cleanerList
    }




    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textTime: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textTime2: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}
//by lazy 晚一點啟用
//MutableLiveData<List<Job>>() 括弧為初始質
