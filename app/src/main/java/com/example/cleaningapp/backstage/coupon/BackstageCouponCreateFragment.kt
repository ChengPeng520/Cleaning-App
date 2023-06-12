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
import com.example.cleaningapp.databinding.FragmentCiyiBackstageCouponCreatBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat

class BackstageCouponCreateFragment : Fragment() {
    private lateinit var binding: FragmentCiyiBackstageCouponCreatBinding
    private val viewModel: BackstageCouponCreateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCiyiBackstageCouponCreatBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btnBsCouponAddPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            //將前台新增coupon的資料填寫完直接按鈕觸發回傳後端,因為有些資料無法雙向綁定
            btCouponCreatAdd.setOnClickListener {
                val coupon = viewModel?.coupon?.value
                coupon?.couponName = tvCouponCreatName.text.toString()
                coupon?.isOnUsed =switch1.isChecked
                coupon?.minPrice = editTextBsCouponMinimum.text.toString().toInt()
                coupon?.count = tvCouponQuantity.text.toString().toInt()
                val dateString = editTextBsCouponCreatLimit.text.toString()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd");
                val date = dateFormat.parse(dateString)
                coupon?.expiredDate = date?.let{
                    Timestamp(it.time)
                }
                //判斷輸入discount的discountType類型,如果是輸入edTextBsCouponDiscount 則為類型顯示false,Percentage則是true
                if (edTextBsCoupnDiscount.text.isNotEmpty()) {
                    coupon?.discount = edTextBsCoupnDiscount.text.toString().toDouble()
                    coupon?.discountType = true
                } else if (edTextBsCouponPercentage.text.isNotEmpty()) {
                    coupon?.discount = edTextBsCouponPercentage.text.toString().toDouble()
                    coupon?.discountType = false
                }
                viewModel?.coupon?.value = coupon
                viewModel?.couponAdd()?.let { result ->
                    if (result) {
                        Toast.makeText(requireContext(), "新增成功", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(it).popBackStack()
                    } else {
                        Toast.makeText(requireContext(), "新增失敗,請重試", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}