package com.example.cleaningapp.customer.csCreateOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCsOrderEstablishedBinding

class CsOrderEstablishedFragment : Fragment() {
    private lateinit var binding: FragmentCsOrderEstablishedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsOrderEstablishedViewModel by viewModels()
        binding = FragmentCsOrderEstablishedBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text = getString(R.string.csTitle_orderEstablished)
        with(binding) {
            btnCsCreateOrderCheckHistory.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.action_csOrderEstablishedFragment_to_historicalorderFragment)
            }
        }
    }
}