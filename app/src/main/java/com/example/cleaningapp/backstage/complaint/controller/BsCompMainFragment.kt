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
import com.example.cleaningapp.backstage.complaint.viewModel.BsCompMainViewModel
import com.example.cleaningapp.backstage.usermanage.controller.UserMainAdapter
import com.example.cleaningapp.databinding.FragmentAlbBsCompMainBinding

class BsCompMainFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsCompMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsCompMainViewModel by viewModels()
        binding = FragmentAlbBsCompMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().title ="客訴管理"
        super.onViewCreated(view, savedInstanceState)
    with(binding){
        rvBsCompMain.layoutManager = LinearLayoutManager(requireContext())
        viewModel?.complaints?.observe(viewLifecycleOwner){ complaints ->
            // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
            if (rvBsCompMain.adapter == null) {
                rvBsCompMain.adapter = ComplaintAdapter(complaints)
            } else {
                (rvBsCompMain.adapter as ComplaintAdapter).updateComplaints(complaints)
            }
        }

        svBsCompMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

        btnBsCompMainDone.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.bsCompDoneFragment)
        }
        }
    }
}