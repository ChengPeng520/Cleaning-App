package com.example.cleaningapp.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R

class ForgetPasswordEmailVFragment : Fragment() {

    companion object {
        fun newInstance() = ForgetPasswordEmailVFragment()
    }

    private lateinit var viewModel: ForgetPasswordEmailVViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rona_forget_password_email_v, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForgetPasswordEmailVViewModel::class.java)
        // TODO: Use the ViewModel
    }

}