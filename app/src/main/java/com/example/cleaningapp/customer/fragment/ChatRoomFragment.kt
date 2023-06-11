package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.chatroom.ChatAdapter
import com.example.cleaningapp.customer.viewModel.ChatRoomViewModel
import com.example.cleaningapp.databinding.FragmentVictorChatRoomBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class ChatRoomFragment : Fragment() {
    private lateinit var binding: FragmentVictorChatRoomBinding
    private val viewModel: ChatRoomViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<BottomNavigationView>(R.id.bvn_customer).visibility =
            View.GONE
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorChatRoomBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text = getString(R.string.csTitle_customerService)
    }

    private fun initRecyclerView() {
        with(binding) {
            rvContactWindowTalk.adapter = ChatAdapter()
            rvContactWindowTalk.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            (rvContactWindowTalk.layoutManager as LinearLayoutManager).stackFromEnd = true
            rvContactWindowTalk.setItemViewCacheSize(500)
            viewModel?.uiState?.observe(viewLifecycleOwner) {
                (rvContactWindowTalk.adapter as ChatAdapter).submitList(it.chatroomItems.toMutableList())
                rvContactWindowTalk.smoothScrollToPosition((rvContactWindowTalk.adapter as ChatAdapter).itemCount)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}