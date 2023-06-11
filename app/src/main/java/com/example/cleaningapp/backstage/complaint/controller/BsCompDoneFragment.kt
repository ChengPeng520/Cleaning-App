package com.example.cleaningapp.backstage.complaint.controller

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
import com.example.cleaningapp.backstage.complaint.viewModel.BsCompDoneViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsCompDoneBinding

class BsCompDoneFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsCompDoneBinding
    val viewModel: BsCompDoneViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbBsCompDoneBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            rvBsCompDone.layoutManager = LinearLayoutManager(requireContext())
            tvBsCompDoneNoSearchData.visibility = View.GONE
            tvBsCompDoneNoData.visibility = View.GONE

            viewModel?.complaints?.observe(viewLifecycleOwner){ complaints ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (rvBsCompDone.adapter == null) {
                    rvBsCompDone.adapter = ComplaintDoneAdapter(complaints)
                } else {
                    (rvBsCompDone.adapter as ComplaintDoneAdapter).updateComplaints(complaints)
                }
            }

            // 顯示尚無資料的判斷
            if (rvBsCompDone.adapter != null && rvBsCompDone.adapter?.itemCount == 0) {
                tvBsCompDoneNoData.visibility = View.VISIBLE
            } else {
                tvBsCompDoneNoData.visibility = View.GONE
            }

            svBsCompDone.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText)

                    //增加查無資料的判斷
                    if (rvBsCompDone.adapter != null && rvBsCompDone.adapter?.itemCount == 0) {
                        tvBsCompDoneNoSearchData.visibility = View.VISIBLE
                        tvBsCompDoneNoData.visibility = View.GONE
                    } else {
                        tvBsCompDoneNoSearchData.visibility = View.GONE
                    }
                    return true
                }
                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })

            btnBsCompDoneDealing.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsCompMainFragment)
            }
        }
    }

}