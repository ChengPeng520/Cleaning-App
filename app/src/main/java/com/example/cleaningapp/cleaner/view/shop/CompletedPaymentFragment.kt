package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.shop.CompletedPaymentViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiCompletedPaymentBinding

class CompletedPaymentFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiCompletedPaymentBinding
    private val viewModel: CompletedPaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiCompletedPaymentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        requireActivity().findViewById<TextView>(R.id.cleaner_toolbar_title).text = "付款完成"
    }

    private fun initView() {
        binding.tvCompletedPaymentBack.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_completedPaymentFragment_to_orderHistoryFragment)
        }
    }
}