package com.example.cleaningapp.customer.csUserPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.adapter.CsViewCouponCouponUseAdapter
import com.example.cleaningapp.databinding.FragmentCsViewCouponBinding

class CsViewCouponFragment : Fragment() {
    private lateinit var binding:FragmentCsViewCouponBinding
    val viewModel: CsViewCouponViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsViewCouponViewModel by viewModels()
        binding = FragmentCsViewCouponBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.csTitle_viewCoupon)
        viewModel.loadCoupons()
        binding.rvCsViewCoupon.layoutManager = LinearLayoutManager(requireContext())
        viewModel?.coupons?.observe(viewLifecycleOwner) { coupons ->
            if (binding.rvCsViewCoupon.adapter == null) {
                binding.rvCsViewCoupon.adapter = CsViewCouponCouponUseAdapter(coupons)
            } else {
                (binding.rvCsViewCoupon.adapter as CsViewCouponCouponUseAdapter).updateCoupons(coupons)
            }
        }
    }
}