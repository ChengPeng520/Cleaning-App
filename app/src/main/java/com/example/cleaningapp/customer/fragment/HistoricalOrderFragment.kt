package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
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
        val drawable = binding.btnOrderListIng.compoundDrawablesRelative[0]
        drawable?.setTint(ContextCompat.getColor(requireContext(), R.color.customerPrimaryDeep))
        binding.btnOrderListIng.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.customerPrimaryDeep
            )
        )
        binding.rvOrderListList.layoutManager = LinearLayoutManager(requireContext())
        val orders = viewModel.orderList.value.orEmpty().filter { it.status == 0 }
        adapter = OrderAdapter(orders)
        binding.rvOrderListList.adapter = adapter
        val defaultTextColor = ContextCompat.getColor(requireContext(), R.color.textSecondary)
        val defaultIconColor = ContextCompat.getColor(requireContext(), R.color.textSecondary)

        binding.btnOrderListIng.setOnClickListener {
            binding.btnOrderListIng.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.customerPrimaryDeep
                )
            )

            // 设置按钮图标颜色
            val drawable = binding.btnOrderListIng.compoundDrawablesRelative[0]
            drawable?.setTint(ContextCompat.getColor(requireContext(), R.color.customerPrimaryDeep))

            // 恢复其他按钮颜色
            binding.btnOrderListEstablished.setTextColor(defaultTextColor)
            binding.btnOrderListDone.setTextColor(defaultTextColor)

            val processingDrawable = binding.btnOrderListEstablished.compoundDrawablesRelative[0]
            processingDrawable?.setTint(defaultIconColor)

            val completedDrawable = binding.btnOrderListDone.compoundDrawablesRelative[0]
            completedDrawable?.setTint(defaultIconColor)
            val orders = viewModel.orderList.value.orEmpty().filter { it.status == 0 }
            adapter?.updateOrders(orders)
        }

        binding.btnOrderListEstablished.setOnClickListener {
            binding.btnOrderListEstablished.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.customerPrimaryDeep
                )
            )

            // 设置按钮图标颜色
            val drawable = binding.btnOrderListEstablished.compoundDrawablesRelative[0]
            drawable?.setTint(ContextCompat.getColor(requireContext(), R.color.customerPrimaryDeep))

            // 恢复其他按钮颜色
            binding.btnOrderListIng.setTextColor(defaultTextColor)
            binding.btnOrderListDone.setTextColor(defaultTextColor)

            val processingDrawable = binding.btnOrderListIng.compoundDrawablesRelative[0]
            processingDrawable?.setTint(defaultIconColor)

            val completedDrawable = binding.btnOrderListDone.compoundDrawablesRelative[0]
            completedDrawable?.setTint(defaultIconColor)
            val orders = viewModel.orderList.value.orEmpty()
                .filter { it.status == 1 || it.status == 2 || it.status == 3 }
            adapter?.updateOrders(orders)
        }

        binding.btnOrderListDone.setOnClickListener {
            binding.btnOrderListDone.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.customerPrimaryDeep
                )
            )

            // 设置按钮图标颜色
            val drawable = binding.btnOrderListDone.compoundDrawablesRelative[0]
            drawable?.setTint(ContextCompat.getColor(requireContext(), R.color.customerPrimaryDeep))

            // 恢复其他按钮颜色
            binding.btnOrderListEstablished.setTextColor(defaultTextColor)
            binding.btnOrderListIng.setTextColor(defaultTextColor)

            val processingDrawable = binding.btnOrderListEstablished.compoundDrawablesRelative[0]
            processingDrawable?.setTint(defaultIconColor)

            val completedDrawable = binding.btnOrderListIng.compoundDrawablesRelative[0]
            completedDrawable?.setTint(defaultIconColor)
            val orders =
                viewModel.orderList.value.orEmpty().filter { it.status == 4 || it.status == 5 || it.status == 6 }
            adapter?.updateOrders(orders)
        }
    }
}