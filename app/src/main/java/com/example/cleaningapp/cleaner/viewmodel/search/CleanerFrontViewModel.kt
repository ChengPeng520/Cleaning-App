package com.example.cleaningapp.cleaner.viewmodel.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.ApplingOrder
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class CleanerFrontViewModel : ViewModel() {
    // 原始Cleaner列表
    // RecyclerView、Adapter
    var cleanerList = mutableListOf<SearchOrder>()

    // 受監控的LiveData，一旦指派新值就會更新Cleaner列表畫面
    val cleaners: MutableLiveData<List<SearchOrder>> by lazy { MutableLiveData<List<SearchOrder>>() }
    val cleaner: MutableLiveData<SearchOrder> by lazy { MutableLiveData<SearchOrder>() }

    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    // 日期、時間篩選
    val chooseCleaningDate: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val chooseCleaningTimeStart: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val chooseCleaningTimeEnd: MutableLiveData<String> by lazy { MutableLiveData<String>("") }

    // 搜尋列表
    init {
        fetchJob()
    }

    private fun fetchJob() {
        requestTask<List<ApplingOrder>>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/clnOrder/select/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
            respBodyType = object : TypeToken<List<ApplingOrder>>() {}.type
        )?.let {
            val searchOrderList : MutableList<SearchOrder> = mutableListOf()
            it.forEach { applingOrder ->
                val searchOrder = SearchOrder(
                    photo = applingOrder.photo,
                    orderId = applingOrder.order.orderId,
                    cleanerId = applingOrder.order.cleanerId,
                    dateOrdered = applingOrder.order.dateOrdered,
                    timeOrderedStart = applingOrder.order.timeOrderedStart,
                    timeOrderedEnd = applingOrder.order.timeOrderedEnd,
                    areaCity = applingOrder.order.areaCity,
                    areaDistrict = applingOrder.order.areaDistrict,
                    areaDetail = applingOrder.order.areaDetail
                )
                searchOrderList.add(searchOrder)
            }
            cleaners.value = searchOrderList
            cleanerList = searchOrderList
        }
    }

    // 前端預設假資料
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

    // 搜尋日期時間篩選
    fun isSearch(): Boolean {
        if (chooseCleaningDate.value.toString()
                .isNotEmpty() && chooseCleaningTimeStart.value.toString()
                .isNotEmpty() && chooseCleaningTimeEnd.value.toString().isNotEmpty()
        ) {
            return true
        }
        return false
    }
}
//by lazy 晚一點啟用
//MutableLiveData<List<Job>>() 括弧為初始質