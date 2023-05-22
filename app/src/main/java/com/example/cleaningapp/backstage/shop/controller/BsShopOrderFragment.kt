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

class BsShopOrderFragment : Fragment() {
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
            btnBsShopOrderProduct.setOnClickListener{
                Navigation.findNavController(it).navigateUp()

            }

            //建立recycleview 的布局,一條列的呈現內部內榮
            rvBsShopOrder.layoutManager = LinearLayoutManager(requireContext())
            //監控ordrerViewModel的週期,如果adapter沒有更新,就呈現orderList的資料,其他有更新的狀態就加上update方法
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
                    viewModel?.search(netText = String())
                    return true
                }

                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })

        }


    }
}


