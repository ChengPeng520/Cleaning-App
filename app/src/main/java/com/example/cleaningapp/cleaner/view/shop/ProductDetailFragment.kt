package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cleaningapp.cleaner.viewmodel.shop.ProductDetailViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiProductDetailBinding

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiProductDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        viewModel.fetchProductDetail(0)
        return binding.root
    }
}