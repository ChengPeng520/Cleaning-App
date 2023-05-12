package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cleaningapp.cleaner.viewmodel.shop.ReceiverInfoViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiReceiverInfoBinding

class ReceiverInfoFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiReceiverInfoBinding
    private val viewModel: ReceiverInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiReceiverInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }
}