package com.example.cleaningapp.backstage.coupon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.coupon.Coupon

/**
 * 單一筆優惠券資料處理
 */
class BackstageCouponViewModel : ViewModel() {
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>() }
}