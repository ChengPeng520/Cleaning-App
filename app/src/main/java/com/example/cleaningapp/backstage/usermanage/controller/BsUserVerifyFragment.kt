package com.example.cleaningapp.backstage.usermanage.controller

import android.opengl.Visibility
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
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserVerifyViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserVerifyBinding

class BsUserVerifyFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserVerifyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserVerifyViewModel by viewModels()
        binding = FragmentAlbBsUserVerifyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvBsUserVerify.layoutManager = LinearLayoutManager(requireContext())
            tvBsUserVerifyNoSearchData.visibility = View.GONE

            viewModel?.users?.observe(viewLifecycleOwner) { users ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (rvBsUserVerify.adapter == null) {
                    rvBsUserVerify.adapter = UserVerifyAdapter(users)
                } else {
                    (rvBsUserVerify.adapter as UserVerifyAdapter).updateUsers(users)
                }

                // 顯示尚無資料的判斷
                if (rvBsUserVerify.adapter != null && rvBsUserVerify.adapter?.itemCount == 0) {
                    tvBsUserVerifyNoData.visibility = View.VISIBLE
                } else {
                    tvBsUserVerifyNoData.visibility = View.GONE
                }
            }

            svBsUserVerifyMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText)
                    if (rvBsUserVerify.adapter != null && rvBsUserVerify.adapter?.itemCount == 0) {
                        tvBsUserVerifyNoSearchData.visibility = View.VISIBLE
                        tvBsUserVerifyNoData.visibility = View.GONE
                    } else {
                        tvBsUserVerifyNoSearchData.visibility = View.GONE
                    }
                    return true
                }

                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })


            btnBsUserVerifyUser.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
            }
            btnBsUserVerifySuspend.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }
            btnBsUserVerifyService.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserServiceFragment)
            }
        }
    }
}