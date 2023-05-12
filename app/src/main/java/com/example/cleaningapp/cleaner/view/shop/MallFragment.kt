package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cleaningapp.cleaner.adapter.ShopAdapter
import com.example.cleaningapp.cleaner.viewmodel.shop.MallViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiMallBinding
import kotlinx.coroutines.launch

class MallFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiMallBinding
    private val viewModel: MallViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiMallBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initRecycler()
        onSearchInputChange()
        return binding.root
    }

    private fun initRecycler() {
        with(binding) {
            rvMallProducts.layoutManager = GridLayoutManager(requireContext(), 2)
            rvMallProducts.adapter = ShopAdapter()
            viewModel?.fetchProducts()
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel?.uiState?.collect {
                        (rvMallProducts.adapter as ShopAdapter).submitList(it.productItems)
                    }
                }
            }
        }
    }

    private fun onSearchInputChange() {
        binding.svMallSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchInputChange(newText)
                return true
            }
        })
    }
}