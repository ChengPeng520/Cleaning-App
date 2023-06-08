package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.viewModel.OrderingViewModel
import com.example.cleaningapp.databinding.FragmentVictorOrderingBinding
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
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("orderId")?.let { orderId ->
            viewModel.fetchOrdersInfo(orderId)
            startRefreshingOrderStatus(orderId)
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
}