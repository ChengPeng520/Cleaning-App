package com.example.cleaningapp.customer.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cleaningapp.customer.ViewModel.DetailedorderViewModel

import com.example.cleaningapp.databinding.FragmentVictorDetailedorderBinding

class DetailedorderFragment : Fragment() {
        private lateinit var binding: FragmentVictorDetailedorderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: DetailedorderViewModel by viewModels()
        binding = FragmentVictorDetailedorderBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

    }
}
