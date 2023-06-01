package com.example.cleaningapp.backstage.coupon


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.requestTask
import com.google.gson.Gson
import com.google.gson.JsonObject


class BackstageCouponModifyViewModel : ViewModel() {
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>(Coupon()) }

    fun couponModify(): Boolean {
        requestTask<Coupon>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/bsCoupon/",
            "PUT",
            coupon.value
        )?.let {
            return true
        }
        return false
    }

    fun fetchCouponInfo(couponId: Int) {
        requestTask<Coupon>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/bsCoupon/$couponId",
            "GET"
        )?.let {
            coupon.value = it
        }
    }
}
