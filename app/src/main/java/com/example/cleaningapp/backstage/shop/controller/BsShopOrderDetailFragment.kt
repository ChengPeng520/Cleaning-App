package com.example.cleaningapp.backstage.shop.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.backstage.shop.OrderDetailAdapter
import com.example.cleaningapp.backstage.shop.viewModel.BsShopOrdersDetailViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopOrderDetailBinding

class BsShopOrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopOrderDetailBinding
    val viewModel: BsShopOrdersDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAlbBsShopOrderDetailBinding.inflate(inflater, container, false)
        binding.viewModel = BsShopOrdersDetailViewModel()
//        binding.viewModel.loadOrdersDetailList("")
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btnBsShopOrderDetailPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            btnBsShopOrderDetailShip.setOnClickListener {
                val shopOrder = viewModel?.shopOrder?.value
                shopOrder?.isShipped = true
                viewModel?.shopOrder?.value = shopOrder
                viewModel?.shopOrderModify()?.let { result ->
                    if (result) {
                        Toast.makeText(requireContext(), "確定出貨", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "資料異常", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            arguments?.let { bundle ->
                bundle.getInt("shopOrderId").let {
                    viewModel?.loadShopOrderList(it)
                    viewModel?.loadOrdersDetailList(it)
                }
            }
            binding.rvBsShopOrderDetail.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.ordersDetail?.observe(viewLifecycleOwner) {
                if (rvBsShopOrderDetail.adapter == null) {
                    rvBsShopOrderDetail.adapter = OrderDetailAdapter(it)
                } else {
                    (rvBsShopOrderDetail.adapter as OrderDetailAdapter).updateDetail(it)

                }
            }
        }
    }
}

