package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cleaningapp.customer.viewModel.ComplaintdetailsViewModel
import com.example.cleaningapp.databinding.FragmentVictorComplaintdetailsBinding

class ComplaintdetailsFragment : Fragment() {
    private lateinit var binding: FragmentVictorComplaintdetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: ComplaintdetailsViewModel by viewModels()
        binding = FragmentVictorComplaintdetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}