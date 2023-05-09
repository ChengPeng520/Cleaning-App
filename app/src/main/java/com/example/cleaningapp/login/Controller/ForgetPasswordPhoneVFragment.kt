package com.example.cleaningapp.login.Controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cleaningapp.R
import com.example.cleaningapp.login.ViewModel.ForgetPasswordPhoneVViewModel

class ForgetPasswordPhoneVFragment : Fragment() {

    companion object {
        fun newInstance() = ForgetPasswordPhoneVFragment()
    }

    private lateinit var viewModel: ForgetPasswordPhoneVViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forget_password_phone_v, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForgetPasswordPhoneVViewModel::class.java)
        // TODO: Use the ViewModel
    }

}