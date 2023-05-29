package com.example.cleaningapp.backstage.shop.controller

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.OrderDetailAdapter
import com.example.cleaningapp.backstage.shop.ShopOrderAdapter
import com.example.cleaningapp.backstage.shop.viewModel.BsShopOrdersDetailViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopOrderDetailBinding

class BsShopOrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopOrderDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: BsShopOrdersDetailViewModel by viewModels()
        binding = FragmentAlbBsShopOrderDetailBinding.inflate(inflater, container, false)
        binding.viewModel = BsShopOrdersDetailViewModel()
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btnBsShopOrderDetailPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            btnBsShopOrderDetailShip.setOnClickListener {

                val radius = resources.getDimensionPixelSize(R.dimen.button_corner_radius).toFloat()
                val shape = GradientDrawable()
                shape.cornerRadius = radius
                btnBsShopOrderDetailShip.background = shape
                // 更改按钮颜色为灰色
                btnBsShopOrderDetailShip.setBackgroundColor(Color.LTGRAY)
                //不能再被點擊
                btnBsShopOrderDetailShip.isEnabled = false
                //文字內容改變成已出貨
                btnBsShopOrderDetailShip.text = "已出貨"
                btnBsShopOrderDetailShip.setTextColor(Color.WHITE)

            }
            arguments?.let { bundle ->
                bundle.getSerializable("product")?.let {
                }
            }
            binding.rvBsShopOrderDetail.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.ordersdetail?.observe(viewLifecycleOwner) {
                if (rvBsShopOrderDetail.adapter == null) {
                    rvBsShopOrderDetail.adapter = OrderDetailAdapter(it)
                } else {
                    (rvBsShopOrderDetail.adapter as OrderDetailAdapter).updateDetail(it)

                }
            }
        }
    }
}

