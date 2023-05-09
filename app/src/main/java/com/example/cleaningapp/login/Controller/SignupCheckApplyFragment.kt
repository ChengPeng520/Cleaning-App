package com.example.cleaningapp.login.Controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cleaningapp.R
import com.example.cleaningapp.login.ViewModel.SignupCheckApplyViewModel

class SignupCheckApplyFragment : Fragment() {

    companion object {
        fun newInstance() = SignupCheckApplyFragment()
    }

    private lateinit var viewModel: SignupCheckApplyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup_check_apply, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupCheckApplyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}