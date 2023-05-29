package com.example.cleaningapp.backstage.usermanage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.Chat
import com.example.cleaningapp.backstage.usermanage.model.Chatroom
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserServiceChatViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserServiceChatBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BsUserServiceChatFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserServiceChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 讓輸入欄在鍵盤跳出時移動到鍵盤上方
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: BsUserServiceChatViewModel by viewModels()
        binding = FragmentAlbBsUserServiceChatBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView(){
        with(binding){
            rvContactWindowTalk.adapter = UserServiceChatAdapter()
            rvContactWindowTalk.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            (rvContactWindowTalk.layoutManager as LinearLayoutManager).stackFromEnd = true
            rvContactWindowTalk.setItemViewCacheSize(500)
            viewModel?.fetchChatRoomTalkList()
            viewModel?.uiState?.observe(viewLifecycleOwner){
                (rvContactWindowTalk.adapter as UserServiceChatAdapter).submitList(it.chatItems.toMutableList())
                rvContactWindowTalk.smoothScrollToPosition((rvContactWindowTalk.adapter as UserServiceChatAdapter).itemCount)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "客服管理"
        arguments?.let { bundle ->
            bundle.getSerializable("chat")?.let {
                binding.viewModel?.chatroom?.value = it as Chatroom
            }
        }

        with(binding) {
            //點選查詢button跳轉,bundle"chat"的參數(customerId 或 cleanerId)
            tvBsUserServChatQuery.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("chat", binding.viewModel?.chatroom?.value)
                Navigation.findNavController(view).navigate(R.id.bsUserMainDetailFragment, bundle)
            }

            ivBsUserServChatBack.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}