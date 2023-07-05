package com.example.cleaningapp.cleaner.viewmodel.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class CleanerFrontViewModel : ViewModel() {
    // 原始Cleaner列表
    // RecyclerView、Adapter
    var cleanerList = listOf<SearchOrder.ApplingOrders>()

    // 受監控的LiveData，一旦指派新值就會更新Cleaner列表畫面
    val cleaners: MutableLiveData<List<SearchOrder.ApplingOrders>> by lazy { MutableLiveData<List<SearchOrder.ApplingOrders>>() }
    val cleaner: MutableLiveData<SearchOrder.ApplingOrders> by lazy { MutableLiveData<SearchOrder.ApplingOrders>() }

    // 日期、時間篩選
    val chooseCleaningDate: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val chooseCleaningTimeStart: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val chooseCleaningTimeEnd: MutableLiveData<String> by lazy { MutableLiveData<String>("") }

    // 搜尋列表 (連線)
    init {
        fetchOrderJob()
    }

    private fun fetchOrderJob() {
        requestTask<List<SearchOrder.ApplingOrders>>(
            "clnOrder/select/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
            "GET",
            respBodyType = object : TypeToken<List<SearchOrder.ApplingOrders>>() {}.type
        )?.let {
            cleaners.value = it
            cleanerList = it
        }
    }

    // 搜尋日期時間 篩選
    fun isSearch(): Boolean {
        if (chooseCleaningDate.value.toString().isNotEmpty() && chooseCleaningDate.value.toString()
                .isNotEmpty()
        ) {
            return true
        }
        return false
    }
}

// 前端預設假資料 - - - - - -

//    init {
//        loadCleaners()
//    }
//
//    /** 模擬取得遠端資料 */
//    private fun loadCleaners() {
//        val cleanerList = mutableListOf<Job>()
//        cleanerList.add(
//            Job(
//                R.drawable.alb_account_avatar, "曾正凡", 1111111,
//                "臺北市", "中山區", "台北市中山區", "臺北市中山區南京東路三段219號5樓", "2023-6-26",
//                "12:00-14:00", "12:00", "14:00",
//                5, 5, 5, 5,
//                "工具:  吸塵器 、拖把\n" +
//                        "整理重點:  臥房、客房 ", R.drawable.after1, null, null, 1111, 0
//            )
//        )
//
//        cleanerList.add(
//            Job(
//                R.drawable.cs_img_xxx, "王濬濬", 22222222,
//                "臺北市", "中山區", "台北市中山區", "臺北市中山區南京東路三段219號6樓", "2023-6-27",
//                "13:00-15:00", "13:00", "15:00",
//                5, 5, 5, 5,
//                "工具:  吸塵器 、拖把\n" +
//                        "整理重點:  臥房、客房 ", R.drawable.after2, null, null, 2222, 0
//            )
//        )
//
//        cleanerList.add(
//            Job(
//                R.drawable.alb_account_avatar, "掰頂姊", 33333333,
//                "臺北市", "中山區", "台北市中山區", "臺北市中山區南京東路三段219號7樓", "2023-6-28",
//                "14:00-16:00", "14:00", "16:00",
//                5, 5, 5, 5,
//                "工具:  吸塵器 、拖把\n" +
//                        "整理重點:  臥房、客房 ", R.drawable.after3, null, null, 3333, 0
//            )
//        )
//
//        this.cleanerList = cleanerList
//        this.cleaner.value = this.cleanerList
//    }


//by lazy 晚一點啟用
//MutableLiveData<List<Job>>() 括弧為初始質