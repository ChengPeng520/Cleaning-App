package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.shop.CompletedPaymentViewModel
import com.example.cleaningapp.cleaner.viewmodel.shop.MallViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiCompleteOrderInfoBinding
import com.example.cleaningapp.databinding.FragmentFatrueiCompletedPaymentBinding
import com.example.cleaningapp.databinding.FragmentFatrueiMallBinding

class CompletedPaymentFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiCompletedPaymentBinding
    private val viewModel: CompletedPaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFatrueiCompletedPaymentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        binding.tvCompletedPaymentBack.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_completedPaymentFragment_to_orderHistoryFragment)
        }
    }
}