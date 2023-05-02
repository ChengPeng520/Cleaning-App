package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.ViewModel.CsOderConfirmedViewModel

class CsOderConfirmedFragment : Fragment() {

    companion object {
        fun newInstance() = CsOderConfirmedFragment()
    }

    private lateinit var viewModel: CsOderConfirmedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cs_oder_confirmed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CsOderConfirmedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}