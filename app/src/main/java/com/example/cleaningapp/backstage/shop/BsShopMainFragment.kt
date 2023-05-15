package com.example.cleaningapp.backstage.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.BackstageShopProductAdapter
import com.example.cleaningapp.backstage.shop.BsShopMainViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopMainBinding

class BsShopMainFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: BsShopMainViewModel by viewModels()
        requireActivity().title = "商城管理"
        binding = FragmentAlbBsShopMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvBsShop.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            viewModel?.products?.observe(viewLifecycleOwner) {
                if (binding.rvBsShop.adapter == null) {
                    binding.rvBsShop.adapter = BackstageShopProductAdapter(it)
                } else {
                    (binding.rvBsShop.adapter as BackstageShopProductAdapter).updateProduct(it)
                }
            }
            btnBsShopMainAdd.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsShopProductAddFragment)
            }
            btnBsShopMainOrder.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsShopOrderFragment)
            }
            svBsShopMain.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
               //
                override fun onQueryTextSubmit(text: String?): Boolean {
                   return false
                }
                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        viewModel?.productSearch(newText)
                    }
                    return true
                }


            })



        }


    }


}