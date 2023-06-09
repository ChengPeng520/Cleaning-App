package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.OrderHistoryAdapter
import com.example.cleaningapp.cleaner.viewmodel.shop.OrderHistoryViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiOrderHistoryBinding

class OrderHistoryFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiOrderHistoryBinding
    private val viewModel: OrderHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiOrderHistoryBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.cleaner_toolbar_title).text = "訂單進度"
        with(binding) {
            rvOrderHistory.layoutManager = LinearLayoutManager(requireContext())
            rvOrderHistory.adapter = OrderHistoryAdapter()
            viewModel?.fetchOrderHistory()
            viewModel?.uiState?.observe(viewLifecycleOwner) {
                (rvOrderHistory.adapter as OrderHistoryAdapter).submitList(it)
            }
        }
    }
}