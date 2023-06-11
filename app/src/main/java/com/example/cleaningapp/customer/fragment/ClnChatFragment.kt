package com.example.cleaningapp.customer.fragment

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
import com.example.cleaningapp.customer.chatroom.ClnChatAdapter
import com.example.cleaningapp.customer.viewModel.ClnChatViewModel
import com.example.cleaningapp.databinding.FragmentVictorClnChatBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ClnChatFragment : Fragment() {
    private lateinit var binding: FragmentVictorClnChatBinding
    private val viewModel: ClnChatViewModel by viewModels()
    private lateinit var messageReceiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<BottomNavigationView>(R.id.bvn_customer).visibility =
            View.GONE
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorClnChatBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        messageReceiver = MessageReceiver()
        registerMessageReceiver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text = getString(R.string.csTitle_contactCleaner)
    }

    private fun initRecyclerView() {
        arguments?.getInt("cleanerId")?.let { viewModel.cleanerId = it }
        with(binding) {
            rvOrderChatroom.layoutManager = LinearLayoutManager(requireContext())
            rvOrderChatroom.adapter = ClnChatAdapter()
            (rvOrderChatroom.layoutManager as LinearLayoutManager).stackFromEnd = true
            rvOrderChatroom.setItemViewCacheSize(500)
            viewModel?.chatroomUiState?.observe(viewLifecycleOwner) {
                (rvOrderChatroom.adapter as ClnChatAdapter).submitList(it.toList())
                rvOrderChatroom.smoothScrollToPosition((rvOrderChatroom.adapter as ClnChatAdapter).itemCount)
            }
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