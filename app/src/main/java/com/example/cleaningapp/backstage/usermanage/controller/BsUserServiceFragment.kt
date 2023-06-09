package com.example.cleaningapp.backstage.usermanage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserServiceViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserServiceBinding

class BsUserServiceFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserServiceViewModel by viewModels()
        binding = FragmentAlbBsUserServiceBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvBsUserServ.layoutManager = LinearLayoutManager(requireContext())
            tvBsUserServQueryNoData.visibility = View.GONE
            tvBsUserServQueryNoSearchData.visibility = View.GONE

            viewModel?.chats?.observe(viewLifecycleOwner) { chats ->
                if (rvBsUserServ.adapter == null) {
                    rvBsUserServ.adapter = UserServiceAdapter(chats)
                } else {
                    (rvBsUserServ.adapter as UserServiceAdapter).updateChats(chats)
                }

                // 顯示尚無資料的判斷
                if (rvBsUserServ.adapter != null && rvBsUserServ.adapter?.itemCount == 0) {
                    tvBsUserServQueryNoData.visibility = View.VISIBLE
                } else {
                    tvBsUserServQueryNoData.visibility = View.GONE
                }
            }

            svBsUserServMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText)
                    if (rvBsUserServ.adapter != null && rvBsUserServ.adapter?.itemCount == 0) {
                        tvBsUserServQueryNoSearchData.visibility = View.VISIBLE
                        tvBsUserServQueryNoData.visibility = View.GONE
                    } else {
                        tvBsUserServQueryNoSearchData.visibility = View.GONE
                    }
                    return true
                }

                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })
            btnBsUserServUser.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
            }
            btnBsUserServVerify.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyFragment)
            }
            btnBsUserServSuspend.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }
        }
    }
}