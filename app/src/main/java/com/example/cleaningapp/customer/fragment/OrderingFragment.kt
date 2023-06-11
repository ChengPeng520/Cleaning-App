package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.TextView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.OrderingViewModel
import com.example.cleaningapp.databinding.FragmentVictorOrderingBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class OrderingFragment : Fragment() {
    private lateinit var binding: FragmentVictorOrderingBinding
    private val timer = Timer()
    private var isRefreshing = false
    val viewModel: OrderingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorOrderingBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAppBarMenu()
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text = getString(R.string.csTitle_orderStatus)
        arguments?.getInt("orderId")?.let { orderId ->
            viewModel.fetchOrdersInfo(orderId)
//            startRefreshingOrderStatus(orderId)
        }
        viewModel.order.observe(viewLifecycleOwner) { order ->
            // 在訂單狀態更新時執行導航操作
            if (order.status == 3) {
                Navigation.findNavController(view)
                    .navigate(R.id.action_orderingFragment_to_ordercompletedFragment, arguments)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopRefreshingOrderStatus()
    }

    private fun startRefreshingOrderStatus(orderId: Int) {
        isRefreshing = true
        val handler = Handler(Looper.getMainLooper())
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (isRefreshing) {
                        // 在这里执行从后端获取最新订单状态的逻辑
                        viewModel.fetchOrdersInfo(orderId)
                    }
                }
            }
        }, 0, 3000)
    }

    private fun stopRefreshingOrderStatus() {
        isRefreshing = false
        timer.cancel()
    }

    private fun initAppBarMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.ment_customer_chatroom, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.chatRoomFragment -> {
                        val bundle = Bundle()
                        bundle.putInt("cleanerId", viewModel.order.value?.cleanerId!!)
                        Navigation.findNavController(
                            requireActivity(),
                            R.id.customer_nav_host_fragment
                        ).navigate(R.id.clnChatFragment, bundle)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<BottomNavigationView>(R.id.bvn_customer).visibility =
            View.VISIBLE
    }
}