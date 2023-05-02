package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.ViewModel.CsChooseCleanerViewModel

class CsChooseCleanerFragment : Fragment() {

    companion object {
        fun newInstance() = CsChooseCleanerFragment()
    }

    private lateinit var viewModel: CsChooseCleanerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cs_choose_cleaner, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CsChooseCleanerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}