package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.customer.ViewModel.OrdercompletedViewModel
import com.example.cleaningapp.R

class OrdercompletedFragment : Fragment() {

    companion object {
        fun newInstance() = OrdercompletedFragment()
    }

    private lateinit var viewModel: OrdercompletedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_victor_ordercompleted, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrdercompletedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}