package com.example.cleaningapp.customer.csHomePage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.adapter.CsCouponObtainAdapter
import com.example.cleaningapp.databinding.FragmentCsCouponObtainBinding

class CsCouponObtainFragment : Fragment() {
    private lateinit var binding: FragmentCsCouponObtainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: CsCouponObtainViewModel by viewModels()
        binding = FragmentCsCouponObtainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text = getString(R.string.csTitle_couponObtain)
        with(binding) {
            rvCouponObtain.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.coupons?.observe(viewLifecycleOwner) { coupons ->
                // adapter為null要建立新的adapter；之後只要呼叫updateCoupons(coupons)即可
                if (rvCouponObtain.adapter == null) {
                    rvCouponObtain.adapter = CsCouponObtainAdapter(coupons)
                }
            }
        }
    }
}