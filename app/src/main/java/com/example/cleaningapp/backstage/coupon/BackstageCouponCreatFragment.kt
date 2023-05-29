package com.example.cleaningapp.backstage.coupon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCiyiBackstageCouponCreatBinding

class BackstageCouponCreatFragment : Fragment() {
    private lateinit var  binding:FragmentCiyiBackstageCouponCreatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
//        val viewModel: BackstageCouponCreatViewModel by viewModels()
////        viewModel.couponAdd()
        binding = FragmentCiyiBackstageCouponCreatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       with(binding){
           btnBsCouponAddPopback.setOnClickListener {
               Navigation.findNavController(it).popBackStack()
           }
           btCouponCreatAdd.setOnClickListener{
               Navigation.findNavController(it).popBackStack()
           }
       }
    }
}