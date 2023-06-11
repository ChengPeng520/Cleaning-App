package com.example.cleaningapp.cleaner.view.order

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.OrderChatroomAdapter
import com.example.cleaningapp.cleaner.uistate.OrderInfo
import com.example.cleaningapp.cleaner.uistate.OrderStateUiState
import com.example.cleaningapp.cleaner.viewmodel.order.OrderChatroomViewModel
import com.example.cleaningapp.databinding.FragmentCleanerOrderChatroomBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CleanerOrderChatroomFragment : Fragment() {
    private lateinit var binding: FragmentCleanerOrderChatroomBinding
    private val viewModel: OrderChatroomViewModel by viewModels()
    private lateinit var messageReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<BottomNavigationView>(R.id.bvn_cleaner).visibility =
            View.GONE
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCleanerOrderChatroomBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setOrder()
        initRecyclerView()
        messageReceiver = MessageReceiver()
        registerMessageReceiver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.cleaner_toolbar_title).text = "對話"
    }

    private fun initRecyclerView() {
        with(binding) {
            rvOrderChatroom.layoutManager = LinearLayoutManager(requireContext())
            rvOrderChatroom.adapter = OrderChatroomAdapter()
            (rvOrderChatroom.layoutManager as LinearLayoutManager).stackFromEnd = true
            rvOrderChatroom.setItemViewCacheSize(500)
            viewModel?.chatroomUiState?.observe(viewLifecycleOwner) {
                (rvOrderChatroom.adapter as OrderChatroomAdapter).submitList(it.toList())
                rvOrderChatroom.smoothScrollToPosition((rvOrderChatroom.adapter as OrderChatroomAdapter).itemCount)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setOrder() {
        (arguments?.getSerializable("order") as OrderStateUiState).run {
            viewModel.setOrderUiState(
                OrderInfo(
                    orderId = orderId,
                    customerId = customerId,
                    dateOrdered = dateOrdered,
                    timeOrderedStart = timeOrderedStart,
                    timeOrderedEnd = timeOrderedEnd,
                    livingRoomSize = livingRoomSize,
                    kitchenSize = kitchenSize,
                    bathRoomSize = bathRoomSize,
                    roomSize = roomSize,
                    priceForCleaner = priceForCleaner
                )
            )
        }
        viewModel.fetchOrderChatRoomTalkList()
    }

    // 註冊廣播接收器攔截"action_chatroom"的廣播
    private fun registerMessageReceiver() {
        val intentFilter = IntentFilter("action_chatroom") //要執行的id
        LocalBroadcastManager.getInstance(requireActivity())
            .registerReceiver(messageReceiver, intentFilter)
    }

    private inner class MessageReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            viewModel.fetchOrderChatRoomTalkList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}