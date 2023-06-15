package com.example.cleaningapp.backstage.coupon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject

class BackstageCouponCreateViewModel : ViewModel() {
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>(Coupon()) }

    fun couponAdd(): Boolean {
        requestTask<JsonObject>(
            "${Constants.BASE_URL}/bsCoupon/",
            "POST",
            coupon.value
        )?.let {
            return it.get("result").toString().toBoolean()
        }
        return false
    }
}