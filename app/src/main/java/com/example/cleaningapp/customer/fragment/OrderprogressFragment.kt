package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.customer.ViewModel.OrderprogressViewModel
import com.example.cleaningapp.R

class OrderprogressFragment : Fragment() {

    companion object {
        fun newInstance() = OrderprogressFragment()
    }

    private lateinit var viewModel: OrderprogressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_victor_orderprogress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderprogressViewModel::class.java)
        // TODO: Use the ViewModel
    }

}