package com.example.cleaningapp.cleaner.view.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.cleaner.adapter.CleanerChatroomAdapter
import com.example.cleaningapp.cleaner.viewmodel.member.CleanerChatroomViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiChatroomBinding

class CleanerChatroomFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiChatroomBinding
    private val viewModel: CleanerChatroomViewModel by viewModels()

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
        binding.rvContactWindowTalk.adapter = CleanerChatroomAdapter()
        binding.rvContactWindowTalk.layoutManager = LinearLayoutManager(requireContext())
        viewModel.fetchChatRoomTalkList()
        viewModel.uiState.observe(viewLifecycleOwner) {
            (binding.rvContactWindowTalk.adapter as CleanerChatroomAdapter).submitList(it.chatroomItems)
        }
    }
}