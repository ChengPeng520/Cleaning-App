package com.example.cleaningapp.cleaner.view.order

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel?.selectedTab?.observe(viewLifecycleOwner) { tabNumber ->
                // 当选项卡发生变化时执行相应的操作
//            handleTabSelection(tabNumber)
            }

            textView62.setOnClickListener {
                viewModel?.onTabSelected(1)
                textView62.setTextColor(Color.BLUE) // 更改字体颜色
                textView49.setTextColor(Color.GRAY)
                imageButton4.setTextColor(Color.GRAY)
//                setTextTintColor(textView62, Color.BLUE)
//                setTextTintColor(textView49, Color.GRAY)
//                setTextTintColor(imageButton4, Color.GRAY)
            }

            textView49.setOnClickListener {
                viewModel?.onTabSelected(2)
                textView49.setTextColor(Color.BLUE) // 更改字体颜色
                textView62.setTextColor(Color.GRAY)
                imageButton4.setTextColor(Color.GRAY)
//                setTextTintColor(textView49, Color.BLUE)
//                setTextTintColor(textView62, Color.GRAY)
//                setTextTintColor(imageButton4, Color.GRAY)
            }

            imageButton4.setOnClickListener {
                viewModel?.onTabSelected(3)
                imageButton4.setTextColor(Color.BLUE) // 更改字体颜色
                textView62.setTextColor(Color.GRAY)
                textView49.setTextColor(Color.GRAY)
//                setTextTintColor(imageButton4, Color.BLUE)
//                setTextTintColor(textView49, Color.GRAY)
//                setTextTintColor(textView62, Color.GRAY)
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

//    private fun setTextTintColor(textView: TextView, color: Int) {
//        DrawableCompat.setTint(
//            DrawableCompat.wrap(textView.background).mutate(),
//            ContextCompat.getColor(requireContext(), R.color.BackGroundGray)
//        )
//    }
}