package com.example.cleaningapp.backstage.usermanage.controller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.backstage.usermanage.model.ChatData
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserServiceChatViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserServiceChatBinding

class BsUserServiceChatFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserServiceChatBinding
    private val viewModel: BsUserServiceChatViewModel by viewModels()
    private lateinit var messageReceiver: BroadcastReceiver

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
        messageReceiver = MessageReceiver()
        registerMessageReceiver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "客服聊天室"
        /**
         * 從ServiceAdapter接過來的bundle
         */
        arguments?.let { bundle ->
            bundle.getSerializable("chatroom")?.let {
                viewModel.chatroom.value = it as ChatData
            }
            bundle.getInt("customerId").let {
                viewModel.customerId = it
            }

            bundle.getInt("cleanerId").let {
                viewModel.cleanerId = it
            }
            viewModel.fetchChatRoomTalkList()
        }
        with(binding) {
            //點選查詢button跳轉,bundle"chat"的參數(customerId 或 cleanerId)
            tvBsUserServChatQuery.setOnClickListener {
//                val bundle = Bundle()
//                viewModel?.chatroom?.value?.accountId?.let { chat ->
//                    bundle.putInt(
//                        "chat",
//                        chat
//                    )
//                }
//                Navigation.findNavController(view)
//                    .navigate(R.id.bsUserMainDetailFragment, bundle)
            }

            ivBsUserServChatBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
//                tvBsUserServChatClose.setOnClickListener {
//                    //TODO:已結案點選將傳回後端作標記,此Id已結案,再傳回到客服前台的recycle view項目標記已結案
//                    val chatroom = binding.viewModel?.chatroom?.value
//                    chatroom?.let {
//                        //傳送請求後端,標記chatroom已結案
//                    }
//                    Navigation.findNavController(it).popBackStack()
//                }
        }
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

    // 註冊廣播接收器攔截"action_chatroom"的廣播
    private fun registerMessageReceiver() {
        val intentFilter = IntentFilter("action_chatroom") //要執行的id
        LocalBroadcastManager.getInstance(requireActivity())
            .registerReceiver(messageReceiver, intentFilter)
    }

    private inner class MessageReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            viewModel.fetchChatRoomTalkList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}