package com.example.cleaningapp.backstage.shop.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.ProductAdapter
import com.example.cleaningapp.databinding.FragmentAlbBsShopMainBinding

class BsShopMainFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopMainBinding
    val viewModel: com.example.cleaningapp.backstage.shop.BsShopMainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        (requireActivity() as BackstageActivity).supportActionBar?.hide()
        binding = FragmentAlbBsShopMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().title = "商城管理"
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvBsShop.layoutManager = GridLayoutManager(requireContext(), 2)
            viewModel?.products?.observe(viewLifecycleOwner) { products ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (rvBsShop.adapter == null) {
                    rvBsShop.adapter = ProductAdapter(products)
                } else {
                    (rvBsShop.adapter as ProductAdapter).updateProducts(products)
                }
                svBsShopMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    // 輸入的文字改變時呼叫
                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText != null) {
                            viewModel?.productSearch(newText)
                        }
                        return true
                    }

                    // 點擊虛擬鍵盤上的提交鈕時呼叫
                    override fun onQueryTextSubmit(text: String): Boolean {
                        return false
                    }
                })
            }

            with(binding) {
                btnBsShopMainAdd.setOnClickListener {
                    Navigation.findNavController(view).navigate(R.id.bsShopProductAddFragment)
                }
                btnBsShopMainOrder.setOnClickListener {
                    Navigation.findNavController(view).navigate(R.id.bsShopOrderFragment)
                }
            }

        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadProducts()
    }
}