package com.example.cleaningapp.cleaner.view.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.CleanerChatroomAdapter
import com.example.cleaningapp.cleaner.viewmodel.member.CleanerChatroomViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiChatroomBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CleanerChatroomFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiChatroomBinding
    private val viewModel: CleanerChatroomViewModel by viewModels()

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
        binding = FragmentFatrueiChatroomBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        with(binding) {
            rvContactWindowTalk.adapter = CleanerChatroomAdapter()
            rvContactWindowTalk.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            (rvContactWindowTalk.layoutManager as LinearLayoutManager).stackFromEnd = true
            rvContactWindowTalk.setItemViewCacheSize(500)
            viewModel?.fetchChatRoomTalkList()
            viewModel?.uiState?.observe(viewLifecycleOwner) {
                (rvContactWindowTalk.adapter as CleanerChatroomAdapter).submitList(it.chatroomItems.toMutableList())
                rvContactWindowTalk.smoothScrollToPosition((rvContactWindowTalk.adapter as CleanerChatroomAdapter).itemCount)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}