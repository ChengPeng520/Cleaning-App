package com.example.cleaningapp.cleaner.view.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.order.OrderCssignCheckViewModel

class OrderCsSignCheckFragment : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderCssignCheckViewModel::class.java)
        // TODO: Use the ViewModel
    }

}