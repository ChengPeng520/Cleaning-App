package com.example.cleaningapp.backstage.coupon

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken


class BackstageCouponSearchViewModel : ViewModel() {
    private var couponList = mutableListOf<Coupon>()
    val coupons: MutableLiveData<List<Coupon>> by lazy { MutableLiveData<List<Coupon>>() }

    init {
        loadCoupons()
    }

    @SuppressLint("SimpleDateFormat")
    fun loadCoupons() {
        requestTask<List<Coupon>>(
            "bsCoupon/",
            "GET",
            respBodyType = object : TypeToken<List<Coupon>>() {}.type
        )?.let {
            coupons.value = it
        }
    }

    fun search(netText: String) {
        if (netText == null || netText.isEmpty()) {
            coupons.value = couponList
        } else {
            val couponSearchList = mutableListOf<Coupon>()
            couponList.forEach { coupon ->
                if (coupon.couponName.contains(netText, true))
                    couponSearchList.add(coupon)
            }
            coupons.value = couponSearchList
        }
    }
}