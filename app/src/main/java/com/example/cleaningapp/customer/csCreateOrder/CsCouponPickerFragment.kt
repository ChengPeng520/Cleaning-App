package com.example.cleaningapp.customer.csCreateOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.adapter.CsCreateOrderCouponUseAdapter
import com.example.cleaningapp.databinding.FragmentCsCouponPickerBinding

class CsCouponPickerFragment : Fragment() {
    private lateinit var binding: FragmentCsCouponPickerBinding
    val viewModel: CsCouponPickerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsCouponPickerViewModel by viewModels()
        binding = FragmentCsCouponPickerBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.csTitle_couponPicker)
        viewModel.fetchCustomerCoupons()
        binding.rvCouponPicker.layoutManager = LinearLayoutManager(requireContext())
        viewModel.coupons.observe(viewLifecycleOwner) { coupons ->
            if (binding.rvCouponPicker.adapter == null) {
                binding.rvCouponPicker.adapter = CsCreateOrderCouponUseAdapter(coupons)
            } else {
                (binding.rvCouponPicker.adapter as CsCreateOrderCouponUseAdapter).updateCoupons(coupons)
            }
        }
    }
}