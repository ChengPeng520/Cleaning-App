package com.example.cleaningapp.customer.csHomePage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.CsHomePageViewModel

class CsHomePageFragment : Fragment() {

    companion object {
        fun newInstance() = CsHomePageFragment()
    }

    private lateinit var viewModel: CsHomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cs_homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CsHomePageViewModel::class.java]
    }
}