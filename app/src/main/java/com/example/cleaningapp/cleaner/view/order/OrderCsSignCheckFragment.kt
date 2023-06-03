package com.example.cleaningapp.cleaner.view.order

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cleaningapp.cleaner.viewmodel.order.OrderCssignCheckViewModel
import com.example.cleaningapp.databinding.FragmentVickyOrderCssignCheckBinding

class OrderCsSignCheckFragment : Fragment() {
    private lateinit var binding: FragmentVickyOrderCssignCheckBinding
    private val viewModel: OrderCssignCheckViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVickyOrderCssignCheckBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val orderId = arguments?.getInt("orderId")
        with(binding) {
            tvOrderRevise.setOnClickListener {
                signatureView.clean()
            }

            button7.setOnClickListener {
                val bitmap = signatureView.drawToBitmap(Bitmap.Config.ARGB_8888)
                findNavController().popBackStack()
            }
        }
    }
}


