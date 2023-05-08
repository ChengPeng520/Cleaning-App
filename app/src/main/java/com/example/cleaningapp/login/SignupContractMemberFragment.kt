package com.example.cleaningapp.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R

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