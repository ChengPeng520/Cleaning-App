package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.customer.ViewModel.ComplaintdetailsViewModel
import com.example.cleaningapp.R

class complaintdetailsFragment : Fragment() {

    companion object {
        fun newInstance() = complaintdetailsFragment()
    }

    private lateinit var viewModel: ComplaintdetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_victor_complaintdetails, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComplaintdetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}