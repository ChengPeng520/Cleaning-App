package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.cleaner.adapter.OrderInfoAdapter
import com.example.cleaningapp.cleaner.uistate.OrderHistoryItemUiState
import com.example.cleaningapp.cleaner.viewmodel.shop.OrderInfoViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiOrderInfoBinding

class OrderInfoFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiOrderInfoBinding
    private val viewModel: OrderInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiOrderInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.orderHistoryItem =
            arguments?.getSerializable("orderHistoryItem") as OrderHistoryItemUiState
        binding.tvOrderInfoState.text =
            if (viewModel.orderHistoryItem.isDelivered) "已送達" else if (viewModel.orderHistoryItem.isShipped) "已出貨" else "以結帳"
        viewModel.fetchOrderInfo(arguments?.getInt("shopOrderId"))
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.rvOrderInfoProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrderInfoProducts.adapter = OrderInfoAdapter()
        viewModel.uiState.observe(viewLifecycleOwner) {
            (binding.rvOrderInfoProducts.adapter as OrderInfoAdapter).submitList(it.orderInfoItems)
        }
    }
}