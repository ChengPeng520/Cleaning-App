package com.example.cleaningapp.customer.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R

class DetailedOrderFragment : Fragment() {

    companion object {
        fun newInstance() = DetailedOrderFragment()
    }

//    private lateinit var viewModel: FragmentDetailedorderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_victor_detailedorder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(FragmentDetailedorderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}