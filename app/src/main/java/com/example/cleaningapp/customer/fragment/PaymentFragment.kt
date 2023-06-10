package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.PaymentViewModel
import com.example.cleaningapp.databinding.FragmentVictorPaymentBinding

class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentVictorPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: PaymentViewModel by viewModels()
        binding = FragmentVictorPaymentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnPayMentOk.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_paymentFragment_to_paydoneFragment)
            }
        }
    }
}