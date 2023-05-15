package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.customer.viewModel.OrderprogressViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentVictorOrderprogressBinding

class OrderprogressFragment : Fragment() {
    private lateinit var binding: FragmentVictorOrderprogressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: OrderprogressViewModel by viewModels()
        binding = FragmentVictorOrderprogressBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}