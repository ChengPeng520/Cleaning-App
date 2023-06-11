package com.example.cleaningapp.customer.csCreateOrder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.CreateOrder
import com.example.cleaningapp.customer.model.CreateOrderPhoto
import com.example.cleaningapp.databinding.FragmentCsOrderConfirmedBinding

class CsOrderConfirmedFragment : Fragment() {
    private lateinit var binding: FragmentCsOrderConfirmedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsOrderConfirmedViewModel by viewModels()
        binding = FragmentCsOrderConfirmedBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        arguments?.let { bundle ->
            bundle.getSerializable("photos")?.let {
                viewModel.photo.value = it as CreateOrderPhoto
                if (viewModel?.photo?.value == null) {
                        binding.llCsCreateOrderPics.visibility = View.GONE
                    }
            }
        }
        if (viewModel.photo == MutableLiveData(CreateOrderPhoto())) {
            binding.llCsCreateOrderPics.visibility = View.GONE
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.setTitle(R.string.csTitle_orderConfirmed)
        with(binding) {
            arguments?.let { bundle ->
                bundle.getSerializable("order")?.let {
                    val orderCreated = it as CreateOrder
                    orderCreated.priceForCustomer =
                        orderCreated.originalPrice - orderCreated.couponDiscount
                    viewModel?.orderCreated?.value = orderCreated
                }
//                bundle.getSerializable("photos")?.let {
//                    viewModel?.photo?.value = it as CreateOrderPhoto
//                    if (viewModel?.photo?.value == null) {
//                        binding.llCsCreateOrderPics.visibility = View.GONE
//                    }
//                }
            }
        }
    }
}