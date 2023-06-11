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
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserSuspendViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserSuspendBinding

class BsUserSuspendFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserSuspendBinding
    val viewModel: BsUserSuspendViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbBsUserSuspendBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvBsUserSusp.layoutManager = LinearLayoutManager(requireContext())
            tvBsUserSuspNoData.visibility = View.GONE
            tvBsUserSuspNoSearchData.visibility = View.GONE

            viewModel?.users?.observe(viewLifecycleOwner) { users ->
                if (rvBsUserSusp.adapter == null) {
                    rvBsUserSusp.adapter = UserSuspendAdapter(users)
                } else {
                    (rvBsUserSusp.adapter as UserSuspendAdapter).updateUsers(users)
                }

                // 顯示尚無資料的判斷
                if (rvBsUserSusp.adapter != null && rvBsUserSusp.adapter?.itemCount == 0) {
                    tvBsUserSuspNoData.visibility = View.VISIBLE
                } else {
                    tvBsUserSuspNoData.visibility = View.GONE
                }
            }

            svBsUserSuspMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText)

                    //新增查無資料顯示
                    if (rvBsUserSusp.adapter != null && rvBsUserSusp.adapter?.itemCount == 0) {
                        tvBsUserSuspNoSearchData.visibility = View.VISIBLE
                        tvBsUserSuspNoData.visibility = View.GONE
                    } else {
                        tvBsUserSuspNoSearchData.visibility = View.GONE
                    }
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