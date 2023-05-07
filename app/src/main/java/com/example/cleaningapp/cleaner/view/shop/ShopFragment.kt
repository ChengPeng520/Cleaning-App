package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cleaningapp.cleaner.viewmodel.shop.ShopViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiShopBinding
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiShopBinding
    private val viewModel: ShopViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiShopBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initRecycler()
        return binding.root
    }

    private fun initRecycler() {
        with(binding) {
            rvShopProducts.layoutManager = GridLayoutManager(requireContext(), 2)
            rvShopProducts.adapter = ShopAdapter()
            viewModel.fetchProducts()
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiState.collect {
                        (rvShopProducts.adapter as ShopAdapter).submitList(it.products)
                    }
                }
            }
        }
    }
}