package com.example.cleaningapp.cleaner.view.order

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.order.OrderCssignCheckViewModel
import com.example.cleaningapp.databinding.FragmentVickyOrderConductBinding
import com.example.cleaningapp.databinding.FragmentVickyOrderCssignCheckBinding

class OrderCsSignCheckFragment : Fragment() {
    private lateinit var binding: FragmentVickyOrderCssignCheckBinding
    private lateinit var signatureViewModel: OrderCssignCheckViewModel


    companion object {
        fun newInstance() = OrderCsSignCheckFragment()
    }

    private lateinit var viewModel: OrderCssignCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vicky_order_cssign_check, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signatureViewModel = ViewModelProvider(this).get(OrderCssignCheckViewModel::class.java)

    }
        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            viewModel = ViewModelProvider(this).get(OrderCssignCheckViewModel::class.java)
            // TODO: Use the ViewModel
        }
    }


