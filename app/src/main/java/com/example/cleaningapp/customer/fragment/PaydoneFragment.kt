package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cleaningapp.customer.viewModel.PaydoneViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentVictorPaydoneBinding

class PaydoneFragment : Fragment() {
    private lateinit var binding: FragmentVictorPaydoneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: PaydoneViewModel by viewModels()
        binding = FragmentVictorPaydoneBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}