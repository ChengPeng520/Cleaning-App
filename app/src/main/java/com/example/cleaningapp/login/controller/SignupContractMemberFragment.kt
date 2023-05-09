package com.example.cleaningapp.login.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cleaningapp.R
import com.example.cleaningapp.login.viewModel.SignupContractMemberViewModel

class SignupContractMemberFragment : Fragment() {

    companion object {
        fun newInstance() = SignupContractMemberFragment()
    }

    private lateinit var viewModel: SignupContractMemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rona_signup_contract_member, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupContractMemberViewModel::class.java)
        // TODO: Use the ViewModel
    }

}