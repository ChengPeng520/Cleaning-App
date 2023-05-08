package com.example.cleaningapp.customer.csHomePage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.CsCouponObtainViewModel

class CsCouponObtainFragment : Fragment() {

    companion object {
        fun newInstance() = CsCouponObtainFragment()
    }

    private lateinit var viewModel: CsCouponObtainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cs_coupon_obtain, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CsCouponObtainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}