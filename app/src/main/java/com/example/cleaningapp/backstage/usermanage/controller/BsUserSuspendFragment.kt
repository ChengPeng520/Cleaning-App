package com.example.cleaningapp.backstage.usermanage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserSuspendViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserSuspendBinding

class BsUserSuspendFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserSuspendBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserSuspendViewModel by viewModels()
        binding = FragmentAlbBsUserSuspendBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvBsUserSusp.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.users?.observe(viewLifecycleOwner) { users ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (rvBsUserSusp.adapter == null) {
                    rvBsUserSusp.adapter = UserSuspendAdapter(users)
                } else {
                    (rvBsUserSusp.adapter as UserSuspendAdapter).updateUsers(users)
                }
            }

            svBsUserSuspMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText)
                    return true
                }

                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })

            btnBsUserSuspUser.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
            }
            btnBsUserSuspVerify.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyFragment)
            }
            btnBsUserSuspService.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserServiceFragment)
            }

        }
    }
}