package com.example.cleaningapp.customer.csHomePage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.CouponObtain
import com.example.cleaningapp.customer.model.CustomerCoupon
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class CsCouponObtainViewModel : ViewModel() {
    val coupons: MutableLiveData<List<CouponObtain>> by lazy { MutableLiveData<List<CouponObtain>>(listOf()) }
    val coupon: MutableLiveData<CouponObtain> by lazy { MutableLiveData<CouponObtain>() }

    init {
        fetchCoupons()
    }

    private fun fetchCoupons() {
        requestTask<List<CouponObtain>>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/csCoupon/1",
            respBodyType = object : TypeToken<List<CouponObtain>>() {}.type
        )?.let {
            coupons.value = it
        }
    }

    fun customerTakeCoupon(customerCoupon: CustomerCoupon): Boolean {
        requestTask<JsonObject>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/customerCoupon/",
            "POST",
            reqBody = customerCoupon
        )?.let {
            val result = it["result"].toString().toBoolean()
            if (result) fetchCoupons()
            return result
        }
        return false
    }
}

/** 模擬取得遠端資料 */
//    private fun loadCoupons() {
//        val couponList = mutableListOf<Coupon>()
//        couponList.add(Coupon(1, false, 0.80, 500, 300, "2023/5/31", true))
//        couponList.add(Coupon(2, false, 0.90, 300, 500, "2023/5/31", true))
//        couponList.add(Coupon(3, true, 100.00, 800, 500, "2023/6/30", false))
//        this.couponList = couponList
//        this.coupons.value = this.couponList
//    }
