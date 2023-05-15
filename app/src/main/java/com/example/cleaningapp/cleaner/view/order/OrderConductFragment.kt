package com.example.cleaningapp.cleaner.view.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.CleanerActivity
import com.example.cleaningapp.cleaner.adapter.OrderAdapter
import com.example.cleaningapp.cleaner.viewmodel.order.OrderConductViewModel
import com.example.cleaningapp.cleaner.viewmodel.search.CleanerFrontViewModel
import com.example.cleaningapp.databinding.FragmentVickyOrderConductBinding

class OrderConductFragment : Fragment() {
    private lateinit var binding: FragmentVickyOrderConductBinding

    companion object {
        fun newInstance() = OrderConductFragment()
    }

    private lateinit var viewModel: OrderConductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as CleanerActivity).supportActionBar?.show()
        val viewModel: OrderConductViewModel by viewModels()
        binding = FragmentVickyOrderConductBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView2.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.order?.observe(viewLifecycleOwner) { orders ->
                if (recyclerView2.adapter == null) {
                    recyclerView2.adapter = OrderAdapter(orders)

                } else {
                    (recyclerView2.adapter as OrderAdapter).updateOrders(orders)
                }
            }
        }

    }
}
