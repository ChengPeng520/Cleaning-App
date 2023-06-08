package com.example.cleaningapp.backstage.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.BackstageActivity
import com.example.cleaningapp.databinding.FragmentBackstageOrderDetailBinding


class BackstageOrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentBackstageOrderDetailBinding
    val viewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (requireActivity() as BackstageActivity).supportActionBar?.show()
        //呈現標題列
        binding = FragmentBackstageOrderDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    //用 onViewCreated 方法完成對視圖的初始化和設置
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {

            btnBsOrderDetailPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            arguments?.getInt("orderId")?.let { viewModel?.fetchBackstageOrderInfo(it) }
        }
    }
}