package com.example.cleaningapp.customer.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.detailed.OrderListViewModel
import com.example.cleaningapp.customer.viewModel.DetailedOrderViewModel

import com.example.cleaningapp.databinding.FragmentVictorDetailedorderBinding

class DetailedOrderFragment : Fragment() {
        private lateinit var binding: FragmentVictorDetailedorderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: OrderListViewModel by viewModels()
        binding = FragmentVictorDetailedorderBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            arguments?.let { bundle ->
                bundle.getSerializable("orderItem")?.let {
                    viewModel?.orderItem?.value = it as Order
            }
        }
            bntApplyComplaint.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_detailedOrderFragment_to_applycomplaintFragment, arguments)
            }
        }
    }
}
