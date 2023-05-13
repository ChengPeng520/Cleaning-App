package com.example.cleaningapp.customer.fragment

import android.os.Binder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.customer.ViewModel.HistoricalorderViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.OrderAdapter
import com.example.cleaningapp.databinding.FragmentVictorHistoricalorderBinding

class HistoricalorderFragment : Fragment() {
    private lateinit var binding: FragmentVictorHistoricalorderBinding
    val viewModel: HistoricalorderViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorHistoricalorderBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchorders()
        binding.rvOrderListList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.orderList.observe(viewLifecycleOwner){
            if (binding.rvOrderListList.adapter == null){
                binding.rvOrderListList.adapter = OrderAdapter(it)
            }
        }
    }
}
