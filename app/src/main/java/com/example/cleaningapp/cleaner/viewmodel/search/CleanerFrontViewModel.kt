package com.example.cleaningapp.cleaner.viewmodel.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

class CleanerFrontViewModel : ViewModel() {
    // 原始Cleaner列表
    // RecyclerView、Adapter
    var cleanerList = listOf<SearchOrder.ApplingOrders>()

    // 受監控的LiveData，一旦指派新值就會更新Cleaner列表畫面
    val cleaners: MutableLiveData<List<SearchOrder.ApplingOrders>> =
        MutableLiveData<List<SearchOrder.ApplingOrders>>()
    val cleaner: MutableLiveData<SearchOrder.ApplingOrders> =
        MutableLiveData<SearchOrder.ApplingOrders>()

    // 日期、時間篩選
    val chooseCleaningDate: MutableLiveData<String> = MutableLiveData<String>("")
    val chooseCleaningTimeStart: MutableLiveData<String> = MutableLiveData<String>("")
    val chooseCleaningTimeEnd: MutableLiveData<String> = MutableLiveData<String>("")

    @OptIn(ExperimentalTime::class)
    val now = TimeSource.Monotonic.markNow()

    @OptIn(ExperimentalTime::class)
    val formattedDateTime = now.toString()

    init {
        fetchOrderJob()
    }

    private fun fetchOrderJob() {
        println("Current start test time: $formattedDateTime")
        requestTask<List<SearchOrder.ApplingOrders>>(
<<<<<<< HEAD
            "clnOrder/select/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
=======
            "${Constants.BASE_URL}/clnOrder/select/${CleanerSharedPreferencesUtils.getCurrentCleanerId()}",
>>>>>>> origin/victor
            "GET",
            respBodyType = object : TypeToken<List<SearchOrder.ApplingOrders>>() {}.type
        )?.let {
            cleaners.value = it
            cleanerList = it
        }
        println("Current end test time: $formattedDateTime")
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