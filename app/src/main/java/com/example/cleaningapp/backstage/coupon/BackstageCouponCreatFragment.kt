package com.example.cleaningapp.backstage.coupon

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R

class BackstageCouponCreatFragment : Fragment() {

    companion object {
        fun newInstance() = BackstageCouponCreatFragment()
    }

    private lateinit var viewModel: BackstageCouponCreatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_backstage_coupon_creat, container, false)
    }




}