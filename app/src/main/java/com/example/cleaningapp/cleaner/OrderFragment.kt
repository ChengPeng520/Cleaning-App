package com.example.cleaningapp.cleaner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        with(binding) {

            btnOrderReserve.isSelected = true
            btnOrderReserve.setTextColor(getColor(requireContext(), R.color.white))

            btnOrderReserve.setOnClickListener {
                btnOrderReserve.isSelected = true
                btnOrderReserve.setTextColor(getColor(requireContext(), R.color.white))
                btnOrderFinish.isSelected = false
                btnOrderFinish.setTextColor(getColor(requireContext(), R.color.black))
            }

            btnOrderFinish.setOnClickListener {
                btnOrderReserve.isSelected = false
                btnOrderReserve.setTextColor(getColor(requireContext(), R.color.black))
                btnOrderFinish.isSelected = true
                btnOrderFinish.setTextColor(getColor(requireContext(), R.color.white))
            }
        }

        return binding.root
    }

}