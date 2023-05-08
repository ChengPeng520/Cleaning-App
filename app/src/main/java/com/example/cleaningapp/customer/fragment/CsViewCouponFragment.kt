package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.ViewModel.CsChooseCleanerViewModel
import com.example.cleaningapp.customer.ViewModel.CsViewCouponViewModel
import com.example.cleaningapp.customer.ViewModel.CsViewCvViewModel
import com.example.cleaningapp.databinding.FragmentCsChooseCleanerBinding
import com.example.cleaningapp.databinding.FragmentCsViewCouponBinding

class CsViewCouponFragment : Fragment() {
    private lateinit var binding:FragmentCsViewCouponBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: CsViewCouponViewModel by viewModels()
        binding = FragmentCsViewCouponBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }


}