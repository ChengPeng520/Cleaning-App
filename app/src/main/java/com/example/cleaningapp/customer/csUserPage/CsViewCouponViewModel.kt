package com.example.cleaningapp.customer.csUserPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Coupon
import com.example.cleaningapp.customer.model.CustomerCoupon
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class CsViewCouponViewModel : ViewModel() {
    //受監控的LiveDATA，一旦指派新值就會更新使用者列表畫面
    val coupons: MutableLiveData<List<Coupon>> by lazy { MutableLiveData<List<Coupon>>() }
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>() }
    //  原始使用者列表
    private var couponList = mutableListOf<Coupon>()

    init {
        fetchCustomerCoupons()
    }

    fun fetchCustomerCoupons() {
        requestTask<List<Coupon>>(
            "${Constants.BASE_URL}/customerCoupon/${CustomerSharePreferencesUtils.getCurrentCustomerId()}",
            method = "GET",
            respBodyType = object : TypeToken<List<Coupon>>() {}.type
        )?.let {
            coupons.value = it
        }
    }

    fun convert(value: String): String {
        return if (value.isNotEmpty()) value.substring(0, 5) else ""
    }
}