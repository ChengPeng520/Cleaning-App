package com.example.cleaningapp.backstage.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cleaningapp.BackstageActivity

import com.example.cleaningapp.databinding.FragmentBackstageOrderDetailBinding


class BackstageOrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentBackstageOrderDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (requireActivity() as BackstageActivity).supportActionBar?.show()
        //呈現標題列
        val viewModel: OrderViewModel by viewModels()
        binding = FragmentBackstageOrderDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    //用 onViewCreated 方法完成對視圖的初始化和設置
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 透過 arguments 屬性來獲取傳遞給該 Fragment 或者 Activity 的 Bundle 對象,
        // 如果 Bundle 不為空，則調用 let 方法，這個方法用於執行一個 Lambda 函數
        arguments?.let { bundle ->
            bundle.getSerializable("order")?.let {
                binding.viewModel?.order?.value =it as Order
                //在 Lambda 函數中，使用 getSerializable 方法來從 Bundle 中獲取一個可序列化的對象，
                // 該對象的名稱為 "friend"。如果該對象不為空，則調用 let 方法執行 Lambda 函數。
            }
        }

    }

}