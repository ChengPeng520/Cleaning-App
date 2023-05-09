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
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserMainViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainBinding

class BsUserMainFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserMainViewModel by viewModels()
        binding = FragmentAlbBsUserMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            rvBsUserMain.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.users?.observe(viewLifecycleOwner){ users ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (rvBsUserMain.adapter == null) {
                    rvBsUserMain.adapter = UserAdapter(users)
                } else {
                    (rvBsUserMain.adapter as UserAdapter).updateUsers(users)
                }
            }

            svBsUserMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

            btnBsUserMainVerify.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyFragment)
            }
            btnBsUserMainSuspend.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }
            btnBsUserMainService.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserServiceFragment)
            }
            btnBsUserMainAdd.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserMainAddFragment)
            }
        }
    }
}