package com.example.cleaningapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class vicky_order_ordercheckFragment : Fragment() {

    companion object {
        fun newInstance() = vicky_order_ordercheckFragment()
    }

    private lateinit var viewModel: VickyOrderOrdercheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vicky_order_ordercheck, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VickyOrderOrdercheckViewModel::class.java)
        // TODO: Use the ViewModel
    }

}