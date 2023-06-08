package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.viewModel.OrderingViewModel
import com.example.cleaningapp.databinding.FragmentVictorOrderingBinding

class OrderingFragment : Fragment() {
    private lateinit var binding: FragmentVictorOrderingBinding
    private lateinit var order: Order
    val viewModel: OrderingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorOrderingBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("orderId")?.let {
            viewModel.fetchOrdersInfo(it)
        }
    }
}