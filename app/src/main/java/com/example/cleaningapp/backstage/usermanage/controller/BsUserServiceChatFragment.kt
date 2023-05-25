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
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserServiceChatViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserServiceChatBinding

class BsUserServiceChatFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserServiceChatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: BsUserServiceChatViewModel by viewModels()
        binding = FragmentAlbBsUserServiceChatBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "客服管理"
        arguments?.let { bundle ->
            bundle.getSerializable("chat")?.let {
                binding.viewModel?.chat?.value = it as Chat
            }
        }

        with(binding) {
            //點選查詢button跳轉,bundle"chat"的參數(customerId 或 cleanerId)
            tvBsUserServChatQuery.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("chat", binding.viewModel?.chat?.value)
                Navigation.findNavController(view).navigate(R.id.bsUserMainDetailFragment, bundle)
            }

            ivBsUserServChatBack.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
        }
    }
}