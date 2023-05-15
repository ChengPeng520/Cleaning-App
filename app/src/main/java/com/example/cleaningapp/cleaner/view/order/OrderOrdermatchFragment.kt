package com.example.cleaningapp.cleaner.view.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.order.OrderOrdermatchViewModel

class OrderOrdermatchFragment : Fragment() {

    companion object {
        fun newInstance() = OrderOrdermatchFragment()
    }

    private lateinit var viewModel: OrderOrdermatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vicky_order_ordermatch, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderOrdermatchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}