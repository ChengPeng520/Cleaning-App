package com.example.cleaningapp.backstage.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCiyiOrderManageBinding

class OrderManageFragment : Fragment() {
    private lateinit var binding: FragmentCiyiOrderManageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        val viewModel : OrderListViewModel by viewModels()
        requireActivity().setTitle(R.string.menu_backstage_orderManage)
        binding = FragmentCiyiOrderManageBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.orders?.observe(viewLifecycleOwner){orders->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(orders)即可
                if (recyclerView.adapter == null ){
                    recyclerView.adapter = OrderAdapter(orders)
                }else{
                    (recyclerView.adapter as OrderAdapter).updateOrders(orders)
                }
            }
            btBsOrderSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(newText: String?): Boolean {
                    return false
                }

                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText.toString())
                    if (recyclerView.adapter != null && recyclerView.adapter?.itemCount ==0 ){
                        tvBsOrderlistNoresult.visibility = View.VISIBLE
                    }else{
                        tvBsOrderlistNoresult.visibility =View.GONE
                    }
                    return  true
                }
            })


        }
    }



}