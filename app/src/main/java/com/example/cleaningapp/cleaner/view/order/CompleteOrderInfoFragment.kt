package com.example.cleaningapp.cleaner.view.order

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.order.CompleteOrderInfoViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiCompleteOrderInfoBinding

class CompleteOrderInfoFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiCompleteOrderInfoBinding
    private val viewModel: CompleteOrderInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiCompleteOrderInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.cleaner_toolbar_title).text = "訂單列表"
        arguments?.getInt("orderId")?.let { viewModel.fetchOrderInfo(it) }
        binding.tvCompleteOrderInfoAddress.setOnClickListener {
            val address = binding.tvCompleteOrderInfoAddress.text.toString()
            googleMaps(address)
        }
    }

    private fun googleMaps(address: String) {
        val intentUri = Uri.parse("google.navigation:q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}