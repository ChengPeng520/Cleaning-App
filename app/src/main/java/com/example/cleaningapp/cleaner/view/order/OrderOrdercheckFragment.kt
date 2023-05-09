package com.example.cleaningapp.cleaner.view.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.order.OrderOrdercheckViewModel

class OrderOrdercheckFragment : Fragment() {

    companion object {
        fun newInstance() = OrderOrdercheckFragment()
    }

    private lateinit var viewModel: OrderOrdercheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vicky_order_ordercheck, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderOrdercheckViewModel::class.java)
        // TODO: Use the ViewModel
    }

}