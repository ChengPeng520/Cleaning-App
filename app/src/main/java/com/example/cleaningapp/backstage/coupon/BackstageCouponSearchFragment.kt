package com.example.cleaningapp.backstage.coupon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCiyiCouponSearchBinding

class BackstageCouponSearchFragment : Fragment() {

    private lateinit var binding: FragmentCiyiCouponSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        requireActivity().setTitle(R.string.menu_backstage_orderManage)
       binding = FragmentCiyiCouponSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.menu_backstage_couponsMange)
        with(binding){
            btCouponAdd.setOnClickListener{
                Navigation.findNavController(it)
                    .navigate(R.id.action_backstageCouponSearchFragment_to_backstageCouponCreatFragment)
            }
        }
    }
}