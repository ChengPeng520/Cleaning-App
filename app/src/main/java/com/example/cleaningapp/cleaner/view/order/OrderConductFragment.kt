package com.example.cleaningapp.cleaner.view.order

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat.setTint
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
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
        initAppBarMenu()
        return binding.root
    }

    private fun initAppBarMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_cleaner_notify, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.notifyFragment -> {
                        Navigation.findNavController(
                            requireActivity(),
                            R.id.cleaner_nav_host_fragment
                        ).navigate(R.id.notifyFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val defaultTextColor = ContextCompat.getColor(requireContext(), R.color.textSecondary)
        val defaultIconColor = ContextCompat.getColor(requireContext(), R.color.textSecondary)

        with(binding) {
            recyclerView2.layoutManager = LinearLayoutManager(requireContext())
            adapter = OrderAdapter(emptyList())
            recyclerView2.adapter = adapter

            viewModel?.loadOrders()
            viewModel?.order?.observe(viewLifecycleOwner) { orders ->
                adapter?.updateOrders(orders)

                // 當選項發生變化時執行相應的操作
//            handleTabSelection(tabNumber: Int)
//            }

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

                    val orders = viewModel?.order?.value.orEmpty().filter { it.status == 1 }
                    adapter?.updateOrders(orders)
                }

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


                    val orders = viewModel?.order?.value.orEmpty().filter { it.status == 2 }
                    adapter?.updateOrders(orders)
                }

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

                    val orders = viewModel?.order?.value.orEmpty().filter { it.status == 3 }
                    adapter?.updateOrders(orders)
                }

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
}
