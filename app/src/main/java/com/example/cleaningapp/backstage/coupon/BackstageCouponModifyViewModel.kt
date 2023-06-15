package com.example.cleaningapp.backstage.coupon


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.Gson
import com.google.gson.JsonObject


class BackstageCouponModifyViewModel : ViewModel() {
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>(Coupon()) }

    fun couponModify(): Boolean {
        requestTask<Coupon>(
            "${Constants.BASE_URL}/bsCoupon/",
            "PUT",
            coupon.value
        )?.let {
            return true
        }
        return false
    }

    fun fetchCouponInfo(couponId: Int) {
        requestTask<Coupon>(
            "${Constants.BASE_URL}/bsCoupon/$couponId",
            "GET"
        )?.let {
            coupon.value = it
            Log.d("TAG","回傳資料:$it")
        }
    }
}
