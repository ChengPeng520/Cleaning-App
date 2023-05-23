package com.example.cleaningapp.cleaner.view.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.cleaner.adapter.OrderChatroomAdapter
import com.example.cleaningapp.cleaner.viewmodel.order.OrderChatroomViewModel
import com.example.cleaningapp.databinding.FragmentCleanerOrderChatroomBinding
import kotlinx.coroutines.launch

class CleanerOrderChatroomFragment : Fragment() {
    private lateinit var binding: FragmentCleanerOrderChatroomBinding
    private val viewModel: OrderChatroomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCleanerOrderChatroomBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        with(binding) {
            rvOrderChatroom.layoutManager = LinearLayoutManager(requireContext())
            rvOrderChatroom.adapter = OrderChatroomAdapter()
            (rvOrderChatroom.layoutManager as LinearLayoutManager).stackFromEnd = true
            rvOrderChatroom.setItemViewCacheSize(500)
            viewModel?.fetchOrderTalk()
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel?.uiState?.collect {
                        (rvOrderChatroom.adapter as OrderChatroomAdapter).submitList(it.orderChatroomItems.toList())
                        rvOrderChatroom.smoothScrollToPosition((rvOrderChatroom.adapter as OrderChatroomAdapter).itemCount)
                    }
                }
            }
        }
    }
}