package com.example.cleaningapp.customer.csCreateOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.CreateOrder
import com.example.cleaningapp.customer.model.CreateOrderPhoto
import com.example.cleaningapp.databinding.FragmentCsOrderConfirmedBinding

class CsOrderConfirmedFragment : Fragment() {
    private lateinit var binding: FragmentCsOrderConfirmedBinding
    private val viewModel: CsOrderConfirmedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCsOrderConfirmedBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text =
            getString(R.string.csTitle_orderConfirmed)
        val bundle = arguments ?: return
        val order = bundle.getSerializable("order") as? CreateOrder
        val orderPhoto = bundle.getParcelable("photos") as? CreateOrderPhoto
        val customerCouponId = bundle.getInt("customerCouponId")

        order?.let {
            it.priceForCustomer = it.originalPrice - it.couponDiscount
            viewModel.orderCreated.value = it
        }

        orderPhoto?.let {
            viewModel.photo.value = it
            if (it.photo1 == null) {
                binding.llCsCreateOrderPics.visibility = View.GONE
            }
        }

        customerCouponId.let {
            viewModel.orderCreated.value?.customerCouponId = it
        }
    }
}