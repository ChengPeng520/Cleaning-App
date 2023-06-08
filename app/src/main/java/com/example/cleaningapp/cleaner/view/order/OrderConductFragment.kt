package com.example.cleaningapp.cleaner.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.CleanerActivity
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.OrderAdapter
import com.example.cleaningapp.cleaner.viewmodel.order.OrderConductViewModel
import com.example.cleaningapp.databinding.FragmentVickyOrderConductBinding

class OrderConductFragment : Fragment() {
    private lateinit var binding: FragmentVickyOrderConductBinding
    private val viewModel: OrderConductViewModel by viewModels()
    private var adapter: OrderAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as CleanerActivity).supportActionBar?.show()
        binding = FragmentVickyOrderConductBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val defaultTextColor = ContextCompat.getColor(requireContext(), R.color.textSecondary)
        val defaultIconColor = ContextCompat.getColor(requireContext(), R.color.textSecondary)

        with(binding) {
            viewModel?.onTabSelected(1)
            textView62.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.cleanerPrimary
                )
            )
            // 設置按鈕圖標颜色
            val drawable = textView62.compoundDrawablesRelative[0]
            drawable?.setTint(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.cleanerPrimary
                )
            )
            recyclerView2.layoutManager = LinearLayoutManager(requireContext())
            val orders = viewModel?.order?.value.orEmpty().filter { it.status == 0 }
            adapter = OrderAdapter(orders)
            recyclerView2.adapter = adapter
            // 當選項發生變化時執行相應的操作

            // 待確認
            textView62.setOnClickListener {
                viewModel?.onTabSelected(1)
                textView62.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )
                // 設置按鈕圖標颜色
                val drawable = textView62.compoundDrawablesRelative[0]
                drawable?.setTint(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )
                // 恢復其他按鈕颜色
                textView49.setTextColor(defaultTextColor)
                imageButton4.setTextColor(defaultTextColor)

                val processingDrawable = textView49.compoundDrawablesRelative[0]
                processingDrawable?.setTint(defaultIconColor)

                val completedDrawable = imageButton4.compoundDrawablesRelative[0]
                completedDrawable?.setTint(defaultIconColor)

                val orders = viewModel?.order?.value.orEmpty().filter { it.status == 0 }
                adapter?.updateOrders(orders)
            }

            // 已成立
            textView49.setOnClickListener {
                viewModel?.onTabSelected(2)

                textView49.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )
                // 設置按鈕圖標颜色
                val drawable = textView49.compoundDrawablesRelative[0]
                drawable?.setTint(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )
                // 恢復其他按鈕颜色


                textView62.setTextColor(defaultTextColor)
                imageButton4.setTextColor(defaultTextColor)

                val processingDrawable = textView62.compoundDrawablesRelative[0]
                processingDrawable?.setTint(defaultIconColor)

                val completedDrawable = imageButton4.compoundDrawablesRelative[0]
                completedDrawable?.setTint(defaultIconColor)


                val orders = viewModel?.order?.value.orEmpty()
                    .filter { it.status == 1 || it.status == 2 || it.status == 3 }
                adapter?.updateOrders(orders)
            }

            // 已結束
            imageButton4.setOnClickListener {
                viewModel?.onTabSelected(3)

                imageButton4.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )

                val drawable = imageButton4.compoundDrawablesRelative[0]
                drawable?.setTint(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )
                // 恢復其他按鈕颜色
                textView62.setTextColor(defaultTextColor)
                textView49.setTextColor(defaultTextColor)

                val processingDrawable = textView62.compoundDrawablesRelative[0]
                processingDrawable?.setTint(defaultIconColor)

                val completedDrawable = textView49.compoundDrawablesRelative[0]
                completedDrawable?.setTint(defaultIconColor)

                val orders = viewModel?.order?.value.orEmpty()
                    .filter { it.status == 4 || it.status == 5 || it.status == 7 }
                adapter?.updateOrders(orders)
            }
        }
    }
}