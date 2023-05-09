package com.example.cleaningapp.cleaner.view.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.cleaner.viewmodel.order.OrderAcceptViewModel
import com.example.cleaningapp.R

class OrderAcceptFragment : Fragment() {

    companion object {
        fun newInstance() = OrderAcceptFragment()
    }

    private lateinit var viewModel: OrderAcceptViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vicky_order_accept, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderAcceptViewModel::class.java)
        // TODO: Use the ViewModel
    }

}