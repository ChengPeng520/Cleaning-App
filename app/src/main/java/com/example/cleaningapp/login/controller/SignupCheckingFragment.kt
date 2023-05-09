package com.example.cleaningapp.login.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cleaningapp.R
import com.example.cleaningapp.login.viewModel.SignupCheckingViewModel

class SignupCheckingFragment : Fragment() {

    companion object {
        fun newInstance() = SignupCheckingFragment()
    }

    private lateinit var viewModel: SignupCheckingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rona_signup_checking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupCheckingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}