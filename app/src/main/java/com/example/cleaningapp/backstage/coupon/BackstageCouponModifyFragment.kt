package com.example.cleaningapp.backstage.coupon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.databinding.FragmentCiyiBackstageCouponModifyBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat

class BackstageCouponModifyFragment : Fragment() {
    private lateinit var binding: FragmentCiyiBackstageCouponModifyBinding
    private val viewModel: BackstageCouponModifyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCiyiBackstageCouponModifyBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            viewModel?.coupon?.observe(viewLifecycleOwner) {
                if (viewModel?.coupon?.value?.discountType == true) {
                    edTextBsCouponModifyDiscount.text = null
                    edTextBsCouponModifyPercentage.setText(viewModel?.coupon?.value?.discount?.toString())
                } else if (viewModel?.coupon?.value?.discountType == false) {
                    edTextBsCouponModifyPercentage.text = null
                    edTextBsCouponModifyDiscount.setText(viewModel?.coupon?.value?.discount?.toString())
                }
            }
            btnBsCouponModifyPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            btCouponModify.setOnClickListener { it ->
                val coupon = viewModel?.coupon?.value
                coupon?.couponName = edTextBsCouponModifyName.text.toString()
                coupon?.isOnUsed = switchModify.isChecked
                coupon?.count = edTextBsCouponModifyCount.text.toString().toInt()
                coupon?.minPrice = editTextBsCouponMinimum.text.toString().toInt()
                val dataString = editTextBsCouponModifyLimit.text.toString()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd");
                val date = dateFormat.parse(dataString)
                coupon?.expiredDate = date?.let {
                    Timestamp(it.time)
                }
                if (edTextBsCouponModifyDiscount.text.isNotEmpty()) {
                    coupon?.discount = edTextBsCouponModifyDiscount.text.toString().toDouble()
                    coupon?.discountType = false
                } else if (edTextBsCouponModifyPercentage.text.isNotEmpty()) {
                    coupon?.discount = edTextBsCouponModifyPercentage.text.toString().toDouble()
                    coupon?.discountType = true
                }
                viewModel?.coupon?.value = coupon
                viewModel?.couponModify()?.let { result ->
                    if (result) {
                        Toast.makeText(requireContext(), "修改成功", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(it)
                    } else {
                        Toast.makeText(requireContext(), "修改失敗,請重試", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
        arguments?.let { bundle ->
            bundle.getInt("couponId").let {
                viewModel.fetchCouponInfo(it)
            }
        }
    }
}