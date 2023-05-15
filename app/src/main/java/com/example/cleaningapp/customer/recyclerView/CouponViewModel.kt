package com.example.cleaningapp.customer.recyclerView

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Coupon

class CouponViewModel: ViewModel() {
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>() }

}