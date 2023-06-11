package com.example.cleaningapp.cleaner.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.order.OrderAcceptViewModel
import com.example.cleaningapp.databinding.FragmentVickyOrderAcceptBinding

class OrderAcceptFragment : Fragment() {
    private lateinit var binding: FragmentVickyOrderAcceptBinding
    private val viewModel: OrderAcceptViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVickyOrderAcceptBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            tvOrderAcceptBackhome.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_order_acceptFragment_to_cleanerFrontFragment)
            }
        }
    }
}