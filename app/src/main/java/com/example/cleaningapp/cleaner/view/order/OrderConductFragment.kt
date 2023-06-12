package com.example.cleaningapp.cleaner.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.OrderAdapter
import com.example.cleaningapp.cleaner.viewmodel.order.OrderConductViewModel
import com.example.cleaningapp.databinding.FragmentVickyOrderConductBinding

class OrderConductFragment : Fragment() {
    private lateinit var binding: FragmentVickyOrderConductBinding
    private val viewModel: OrderConductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVickyOrderConductBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.cleaner_toolbar_title).text = "訂單紀錄"
        val defaultTextColor = ContextCompat.getColor(requireContext(), R.color.textSecondary)
        val defaultIconColor = ContextCompat.getColor(requireContext(), R.color.textSecondary)
        with(binding) {
            initView()
            initRecyclerView()
//            當選項發生變化時執行相應的操作
//            待確認
            textView62.setOnClickListener {
                textView62.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )
                // 設置按鈕圖標颜色
                textView62.compoundDrawablesRelative[0].setTint(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )
                // 恢復其他按鈕颜色
                textView49.setTextColor(defaultTextColor)
                imageButton4.setTextColor(defaultTextColor)
                textView49.compoundDrawablesRelative[0].setTint(defaultIconColor)
                imageButton4.compoundDrawablesRelative[0].setTint(defaultIconColor)
                viewModel?.order?.value = viewModel?.orderList.orEmpty().filter { it.status == 0 }
            }

            // 已成立
            textView49.setOnClickListener {
                textView49.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )
                // 設置按鈕圖標颜色
                textView49.compoundDrawablesRelative[0]?.setTint(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )

                // 恢復其他按鈕颜色
                textView62.setTextColor(defaultTextColor)
                imageButton4.setTextColor(defaultTextColor)
                textView62.compoundDrawablesRelative[0].setTint(defaultIconColor)
                imageButton4.compoundDrawablesRelative[0].setTint(defaultIconColor)
                viewModel?.order?.value = viewModel?.orderList.orEmpty()
                    .filter { it.status == 1 || it.status == 2 || it.status == 3 }
            }

            // 已結束
            imageButton4.setOnClickListener {
                imageButton4.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )

                imageButton4.compoundDrawablesRelative[0]?.setTint(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cleanerPrimary
                    )
                )
                // 恢復其他按鈕颜色
                textView62.setTextColor(defaultTextColor)
                textView49.setTextColor(defaultTextColor)
                textView62.compoundDrawablesRelative[0]?.setTint(defaultIconColor)
                textView49.compoundDrawablesRelative[0].setTint(defaultIconColor)
                viewModel?.order?.value = viewModel?.orderList.orEmpty()
                    .filter { it.status == 4 || it.status == 5 || it.status == 6 || it.status == 7 }
            }
        }
    }

    private fun initView() {
        val defaultTextColor = ContextCompat.getColor(requireContext(), R.color.textSecondary)
        val defaultIconColor = ContextCompat.getColor(requireContext(), R.color.textSecondary)
        with(binding) {
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
            textView49.setTextColor(defaultTextColor)
            imageButton4.setTextColor(defaultTextColor)
            textView49.compoundDrawablesRelative[0].setTint(defaultIconColor)
            imageButton4.compoundDrawablesRelative[0].setTint(defaultIconColor)
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            recyclerView2.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.order?.observe(viewLifecycleOwner) {
                if (recyclerView2.adapter == null) {
                    recyclerView2.adapter = OrderAdapter(it)
                } else {
                    (recyclerView2.adapter as OrderAdapter).updateOrders(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchOrderRecord()
    }
}