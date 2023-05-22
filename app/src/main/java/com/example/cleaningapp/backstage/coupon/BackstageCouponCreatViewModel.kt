package com.example.cleaningapp.backstage.coupon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Member(val username: String, val password: String, val nickname: String)
class BackstageCouponCreatViewModel:ViewModel() {
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>() }

//    fun couponAdd(){
//        val coupon = Member(
//            username = coupon.value?.num ?:"",
//            password = coupon.value?.name ?:"",
//            nickname = coupon.value?.percentage ?:"",
//        )
//        val result = requestTask<Boolean>(
//            "http://10.0.2.2:8080/javaweb-exercise-00/member/findAll",
//            "POST",
//            coupon
//        )
//        print(result)
    }
