package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.ViewModel.CsViewCouponViewModel

class CsViewCouponFragment : Fragment() {

    companion object {
        fun newInstance() = CsViewCouponFragment()
    }

    private lateinit var viewModel: CsViewCouponViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cs_view_coupon, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CsViewCouponViewModel::class.java)
        // TODO: Use the ViewModel
    }

}