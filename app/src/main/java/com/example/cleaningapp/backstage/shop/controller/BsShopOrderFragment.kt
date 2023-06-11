package com.example.cleaningapp.backstage.shop.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.backstage.shop.ShopOrderAdapter
import com.example.cleaningapp.backstage.shop.viewModel.BsShopOrdersViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopOrderBinding

class BsShopOrderFragment : Fragment(),ShopOrderAdapter.onItemClickListener {
    private lateinit var binding: FragmentAlbBsShopOrderBinding
    val viewModel: BsShopOrdersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentAlbBsShopOrderBinding.inflate(inflater, container, false)
        binding.viewModel = BsShopOrdersViewModel()
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            rvBsShopOrder.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.shopOrders?.observe(viewLifecycleOwner) {
                if (rvBsShopOrder.adapter == null) {
                    rvBsShopOrder.adapter = ShopOrderAdapter(it)
                } else {
                    (rvBsShopOrder.adapter as ShopOrderAdapter).updateShopOrders(it)
                }
            }
            svBsShopOrder.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        viewModel?.search(newText)
                    }
                    if (rvBsShopOrder.adapter != null && rvBsShopOrder.adapter?.itemCount == 0) {
                        tvBsProductOrderNoResult.visibility = View.VISIBLE
                    } else {
                        tvBsProductOrderNoResult.visibility = View.GONE
                    }
                    return true
                }

                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })
            btnBsShopOrderProduct.setOnClickListener {
                Navigation.findNavController(it).navigateUp()
            }
            }

        }
    override fun onResume() {
        super.onResume()
        viewModel.loadOrderList()
    }

    override fun onItemClick(shopOrderId: Int) {

    }
//    fun getMatchDataId(shopOrderId: Int): ShopOrderList?{
//        val matchDataId = .find {  }
//    }

}



