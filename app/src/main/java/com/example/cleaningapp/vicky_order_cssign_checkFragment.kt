package com.example.cleaningapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class vicky_order_cssign_checkFragment : Fragment() {

    companion object {
        fun newInstance() = vicky_order_cssign_checkFragment()
    }

    private lateinit var viewModel: VickyOrderCssignCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vicky_order_cssign_check, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VickyOrderCssignCheckViewModel::class.java)
        // TODO: Use the ViewModel
    }

}