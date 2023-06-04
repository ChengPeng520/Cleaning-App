package com.example.cleaningapp.cleaner.view.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.OrderChatroomAdapter
import com.example.cleaningapp.cleaner.viewmodel.order.OrderChatroomViewModel
import com.example.cleaningapp.databinding.FragmentCleanerOrderChatroomBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CleanerOrderChatroomFragment : Fragment() {
    private lateinit var binding: FragmentCleanerOrderChatroomBinding
    private val viewModel: OrderChatroomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<BottomNavigationView>(R.id.bvn_cleaner).visibility =
            View.GONE
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCleanerOrderChatroomBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
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
            viewModel?.chatroomUiState?.observe(viewLifecycleOwner) {
                (rvOrderChatroom.adapter as OrderChatroomAdapter).submitList(it.toList())
                rvOrderChatroom.smoothScrollToPosition((rvOrderChatroom.adapter as OrderChatroomAdapter).itemCount)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}