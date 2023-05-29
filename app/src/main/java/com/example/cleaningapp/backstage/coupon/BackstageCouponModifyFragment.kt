package com.example.cleaningapp.backstage.coupon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCiyiBackstageCouponModifyBinding

class BackstageCouponModifyFragment : Fragment() {
    private lateinit var binding: FragmentCiyiBackstageCouponModifyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: BackstageCouponViewModel by viewModels()
        binding = FragmentCiyiBackstageCouponModifyBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel =viewModel
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            btnBsCouponModifyPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            btCouponModifyAdd.setOnClickListener{
                Navigation.findNavController(it).popBackStack()
            }

        }
        arguments?.let { bundle ->
            bundle.getSerializable("coupon")?.let {
                binding.viewmodel?.coupon?.value = it as Coupon
            }
        }
    }
}