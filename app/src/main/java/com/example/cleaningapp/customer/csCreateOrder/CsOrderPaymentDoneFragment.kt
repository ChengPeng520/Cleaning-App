package com.example.cleaningapp.customer.csCreateOrder

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.shop.CompletedPaymentViewModel
import com.example.cleaningapp.databinding.FragmentCsOrderPaymentDoneBinding
import com.example.cleaningapp.databinding.FragmentFatrueiCompletedPaymentBinding

class CsOrderPaymentDoneFragment : Fragment() {
    private lateinit var binding: FragmentCsOrderPaymentDoneBinding
    private val viewModel: CsOrderPaymentDoneViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCsOrderPaymentDoneBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text = "付款完成"
    }

    private fun initView() {
        binding.tvCsCreateOrderCheckHistory.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_csOrderPaymentDoneFragment_to_historicalorderFragment)
        }
    }

}