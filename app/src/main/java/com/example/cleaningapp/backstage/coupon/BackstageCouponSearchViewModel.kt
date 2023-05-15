package com.example.cleaningapp.backstage.coupon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.coupon.Coupon

class BackstageCouponSearchViewModel : ViewModel() {
    private var couponList = mutableListOf<Coupon>()
    val coupons: MutableLiveData<List<Coupon>> by lazy { MutableLiveData<List<Coupon>>() }

    init {
        loadCoupons()

    }

    private fun loadCoupons() {

        val couponList = mutableListOf<Coupon>()
        couponList.add(
            Coupon(
                "123456789", "跳樓折100", "30%折",
                "30", 100, "2023-09-09", "3000")
        )
        this.couponList =couponList
        this.coupons.value =this.couponList


    }
}