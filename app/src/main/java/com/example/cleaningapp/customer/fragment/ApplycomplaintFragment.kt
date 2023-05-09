package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.customer.viewModel.ApplycomplaintViewModel
import com.example.cleaningapp.R

class ApplycomplaintFragment : Fragment() {

    companion object {
        fun newInstance() = ApplycomplaintFragment()
    }

    private lateinit var viewModel: ApplycomplaintViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_victor_applycomplaint, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ApplycomplaintViewModel::class.java)
        // TODO: Use the ViewModel
    }

}