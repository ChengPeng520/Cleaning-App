package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.detailed.OrderAdapter
import com.example.cleaningapp.customer.viewModel.HistoricalOrderViewModel
import com.example.cleaningapp.databinding.FragmentVictorHistoricalorderBinding

class HistoricalOrderFragment : Fragment() {
    private lateinit var binding: FragmentVictorHistoricalorderBinding
    private val viewModel: HistoricalOrderViewModel by viewModels()
    private var adapter: OrderAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorHistoricalorderBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvOrderListList.layoutManager = LinearLayoutManager(requireContext())

        adapter = OrderAdapter(emptyList())
        binding.rvOrderListList.adapter = adapter

        viewModel.fetchOrders()
        viewModel.orderList.observe(viewLifecycleOwner) { orders ->
            adapter?.updateOrders(orders)
        }

        binding.btnOrderListIng.setOnClickListener {
            val orders = viewModel.orderList.value.orEmpty().filter { it.status == 1 }
            adapter?.updateOrders(orders)
        }

        binding.btnOrderListEstablished.setOnClickListener {
            val orders = viewModel.orderList.value.orEmpty().filter { it.status == 2 }
            adapter?.updateOrders(orders)
        }

        binding.btnOrderListDone.setOnClickListener {
            val orders = viewModel.orderList.value.orEmpty().filter { it.status == 3 }
            adapter?.updateOrders(orders)
        }
    }
}
