package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.customer.ViewModel.PaydoneViewModel
import com.example.cleaningapp.R

class PaydoneFragment : Fragment() {

    companion object {
        fun newInstance() = PaydoneFragment()
    }

    private lateinit var viewModel: PaydoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_victor_paydone, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PaydoneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}