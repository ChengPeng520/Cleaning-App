package com.example.cleaningapp.customer.csUserPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Coupon

class CsViewCouponViewModel : ViewModel() {
    //受監控的LiveDATA，一旦指派新值就會更新使用者列表畫面
    val coupons: MutableLiveData<List<Coupon>> by lazy { MutableLiveData<List<Coupon>>() }
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>() }
    //  原始使用者列表
    private var couponList = mutableListOf<Coupon>()

    init {
        loadCoupons()
    }

    fun loadCoupons() {
//        val couponList = mutableListOf<Coupon>()
//        couponList.add(Coupon(1, "xxx", true, 100.0, 300, 500, expiredDate = "2023/06/30", isOnUsed = true))
//        couponList.add(Coupon(2, "ooo", false,  0.8,500, 500, expiredDate = "2023/08/31", isOnUsed = true))
//        this.couponList = couponList
//        this.coupons.value = this.couponList
    }
}