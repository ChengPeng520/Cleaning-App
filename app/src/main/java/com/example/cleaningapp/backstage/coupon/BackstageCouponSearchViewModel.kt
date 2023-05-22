package com.example.cleaningapp.backstage.coupon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
                "30", "100","2023-09-09", "3000", true
            )
        )
        couponList.add(
            Coupon(
                "123456666", "跳樓折200", "40%折",
                "30", "200", "2023-09-09", "3000", true
            )
        )

        this.couponList = couponList
        this.coupons.value = this.couponList

    }

    fun search(netText: String) {
        if (netText == null || netText.isEmpty()) {
            coupons.value = couponList
        } else {
            val couponSearchList = mutableListOf<Coupon>()
            couponSearchList.forEach { coupon ->
                if (coupon.num.contains(netText, true) || coupon.name.contains(netText, true))
                    couponSearchList.add(coupon)
            }
            coupons.value = couponSearchList
        }

    }

}