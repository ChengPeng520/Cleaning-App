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
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.order.OrderOrdermatchViewModel
import com.example.cleaningapp.databinding.FragmentVickyOrderOrdermatchBinding

class OrderOrdermatchFragment : Fragment() {
    private lateinit var binding: FragmentVickyOrderOrdermatchBinding
    private val viewModel: OrderOrdermatchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVickyOrderOrdermatchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.cleaner_toolbar_title).text = "訂單媒合中"
        arguments?.getInt("orderId")?.let {
            viewModel.fetchOrderMatch(it)
            viewModel.orderId = it
        }

        with(binding) {
            textView57.setOnClickListener {
                val address = binding.textView57.text.toString()
                googleMaps(address)
            }
            tvOrderCancel.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_vicky_order_ordermatchFragment_to_vicky_order_conductFragment)
            }
        }
    }

    private fun googleMaps(address: String) {
        val intentUri = Uri.parse("google.navigation:q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}


