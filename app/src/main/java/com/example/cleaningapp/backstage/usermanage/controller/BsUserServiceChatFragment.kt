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
import com.example.cleaningapp.backstage.usermanage.model.ChatClnBack
import com.example.cleaningapp.backstage.usermanage.model.Chatroom
import com.example.cleaningapp.backstage.usermanage.model.Member
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserServiceChatViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserServiceChatBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BsUserServiceChatFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserServiceChatBinding
    private val viewModel: BsUserServiceChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 讓輸入欄在鍵盤跳出時移動到鍵盤上方
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAlbBsUserServiceChatBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        with(binding) {
            rvContactWindowTalk.adapter = UserServiceChatAdapter()
            rvContactWindowTalk.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            (rvContactWindowTalk.layoutManager as LinearLayoutManager).stackFromEnd = true
            rvContactWindowTalk.setItemViewCacheSize(500)
            viewModel?.uiState?.observe(viewLifecycleOwner) {
                (rvContactWindowTalk.adapter as UserServiceChatAdapter).submitList(it.chatItems.toMutableList())
                rvContactWindowTalk.smoothScrollToPosition((rvContactWindowTalk.adapter as UserServiceChatAdapter).itemCount)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "客服聊天室"

        /**
         * 從ServiceAdapter接過來的bundle
         */
        arguments?.let { bundle ->
            bundle.getInt("cleanerId")?.let {
                binding.viewModel?.cleanerId = it
            }

            with(binding) {
                //點選查詢button跳轉,bundle"chat"的參數(customerId 或 cleanerId)
                tvBsUserServChatQuery.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putSerializable("chat", binding.viewModel?.chatroom?.value)
                    Navigation.findNavController(view)
                        .navigate(R.id.bsUserMainDetailFragment, bundle)
                }

                ivBsUserServChatBack.setOnClickListener {
                    Navigation.findNavController(it).popBackStack()
                }
                tvBsUserServChatClose.setOnClickListener {
                    //TODO:已結案點選將傳回後端作標記,此Id已結案,再傳回到客服前台的recycle view項目標記已結案
                    val chatroom = binding.viewModel?.chatroom?.value
                    chatroom?.let {
                        //傳送請求後端,標記chatroom已結案
                    }
                    Navigation.findNavController(it).popBackStack()
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}