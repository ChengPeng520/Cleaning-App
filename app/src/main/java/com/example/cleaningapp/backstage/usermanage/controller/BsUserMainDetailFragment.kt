package com.example.cleaningapp.backstage.usermanage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.Chat
import com.example.cleaningapp.backstage.usermanage.model.Chatroom
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserMainDetailViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainDetailBinding

class BsUserMainDetailFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserMainDetailBinding
    val viewModel: BsUserMainDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAlbBsUserMainDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().title = "用戶管理"
        arguments?.let { bundle ->
            bundle.getSerializable("user")?.let {
                binding.viewModel?.user?.value = it as User
            }
            //新增從客服聊天室點選查詢,bundle customerId或cleanerId的會員資料解讀
            arguments?.let { chatBundle ->
                chatBundle.getSerializable("chat")?.let {
                    if ((it as Chatroom).customerId == 0) {
                        viewModel.fetchMemberInfo(it.cleanerId)
                    } else {
                        viewModel.fetchMemberInfo(it.customerId)
                    }
                }
            }

            with(binding) {
                btnBsUserMainDetailModify.setOnClickListener {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_bsUserMainDetailFragment_to_bsUserMainModifyFragment)
                }
                btnBsUserMainDetailDelete.setOnClickListener {
                    Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
                }
                ivBsUserMainDetailBack.setOnClickListener {
                    Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
                }
            }
        }
    }
}