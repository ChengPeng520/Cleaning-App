package com.example.cleaningapp.customer.csUserPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Coupon

class CsViewCouponViewModel : ViewModel() {
    //受監控的LiveDATA，一旦指派新值就會更新使用者列表畫面
    val coupons: MutableLiveData<List<Coupon>> by lazy { MutableLiveData<List<Coupon>>() }
    //  原始使用者列表
    private var couponList = mutableListOf<Coupon>()

    init {
        loadCoupons()
    }

    fun loadCoupons() {
        val couponList = mutableListOf<Coupon>()
        couponList.add(Coupon(1, 0, 0.80, 500, 300, "2023/5/31", true))
        couponList.add(Coupon(2, 0, 0.90, 300, 500, "2023/5/31", true))
        this.couponList = couponList
        this.coupons.value = this.couponList
    }
}