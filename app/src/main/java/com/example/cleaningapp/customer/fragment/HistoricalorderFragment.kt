package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.customer.ViewModel.HistoricalorderViewModel
import com.example.cleaningapp.R

class HistoricalorderFragment : Fragment() {

    companion object {
        fun newInstance() = HistoricalorderFragment()
    }

    private lateinit var viewModel: HistoricalorderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historicalorder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoricalorderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}