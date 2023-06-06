package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.ShoppingCartAdapter
import com.example.cleaningapp.cleaner.uistate.ShoppingCartItemUiState
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
        binding.lifecycleOwner = this
        initView()
        return binding.root
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
            rvShoppingCartProduct.layoutManager = LinearLayoutManager(requireContext())
            rvShoppingCartProduct.adapter = ShoppingCartAdapter()
            viewModel?.uiState?.observe(viewLifecycleOwner) {
                (rvShoppingCartProduct.adapter as ShoppingCartAdapter).submitList(it.toList())
            }

            rvShoppingCartProduct.post {
                kotlin.run {
                    (rvShoppingCartProduct.adapter as ShoppingCartAdapter).setOnclick(object :
                        ShoppingCartAdapter.ClickInterface {
                        override fun onBtnClick(productId: ShoppingCartItemUiState) {
                            viewModel?.deleteProduct(productId)?.let {
                                if (it) {
                                    viewModel?.fetchShopOrderList()
                                    Toast.makeText(requireContext(), "刪除成功", Toast.LENGTH_SHORT)
                                        .show()
                                } else Toast.makeText(requireContext(), "刪除失敗", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onBtnPlusOrMinus(
                            productId: ShoppingCartItemUiState,
                            state: Boolean
                        ) {
                            viewModel?.updateNumber(productId, state)
                        }
                    })
                }
            }
        }
    }
}