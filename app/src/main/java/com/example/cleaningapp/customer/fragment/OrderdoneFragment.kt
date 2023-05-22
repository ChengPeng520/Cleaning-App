package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cleaningapp.customer.viewModel.OrderdoneViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentVictorOrderdoneBinding

class OrderdoneFragment : Fragment() {
    private lateinit var binding: FragmentVictorOrderdoneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: OrderdoneViewModel by viewModels()
        binding = FragmentVictorOrderdoneBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

}