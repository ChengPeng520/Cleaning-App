package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.ShoppingCartAdapter
import com.example.cleaningapp.cleaner.viewmodel.shop.ShoppingCartViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiShoppingCartBinding

class ShoppingCartFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiShoppingCartBinding
    private val viewModel: ShoppingCartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiShoppingCartBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        binding.cLShoppingCartReceiverInfo.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_shoppingCartFragment_to_receiverInfoFragment)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding) {
            viewModel?.fetchCleanerShoppingCartInfo()
            rvShoppingCartProduct.layoutManager = LinearLayoutManager(requireContext())
            rvShoppingCartProduct.adapter = ShoppingCartAdapter()
            viewModel?.uiState?.observe(viewLifecycleOwner) {
                print(it.shoppingCartItems.size)
                (rvShoppingCartProduct.adapter as ShoppingCartAdapter).submitList(it.shoppingCartItems)
            }
        }
    }
}