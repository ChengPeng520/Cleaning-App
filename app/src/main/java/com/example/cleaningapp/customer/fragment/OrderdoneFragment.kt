package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.customer.ViewModel.OrderdoneViewModel
import com.example.cleaningapp.R

class OrderdoneFragment : Fragment() {

    companion object {
        fun newInstance() = OrderdoneFragment()
    }

    private lateinit var viewModel: OrderdoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_victor_orderdone, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderdoneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}