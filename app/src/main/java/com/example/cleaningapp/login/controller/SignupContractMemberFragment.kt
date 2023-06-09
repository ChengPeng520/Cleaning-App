package com.example.cleaningapp.login.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaSignupContractMemberBinding
import com.example.cleaningapp.login.viewModel.SignupContractMemberViewModel

class SignupContractMemberFragment : Fragment() {
    private lateinit var binding: FragmentRonaSignupContractMemberBinding

    //    companion object {
//
//        private const val accountKey = "account"
//        fun getBundle(account: String) = Bundle().apply {
//            putString(accountKey, account)
//        }
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: SignupContractMemberViewModel by viewModels()
        binding = FragmentRonaSignupContractMemberBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<Toolbar>(R.id.login_toolbar).visibility = View.VISIBLE
        requireActivity().findViewById<TextView>(R.id.login_toolbar_title).text = "會員條款"
        with(binding) {
            ivContractMemberBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            btnContractMemberConfirm.setOnClickListener {
                if (rdoBtnContractMemberRead.isChecked) {
                    val bundle = arguments
                    Navigation.findNavController(it)
                        .navigate(
                            R.id.action_signupContractMemberFragment_to_signupMemberInfoFragment,
                            bundle
                        )
                } else {
                    Toast.makeText(context, "尚未同意規範", Toast.LENGTH_SHORT).show()
                }
            }

            val inputStream = requireContext().assets.open("signup_contract_member.txt")
            inputStream.bufferedReader().useLines { lines ->
                val contractMember = lines.fold("") { n, line ->
                    "$n$line\n"
                }
                viewModel?.contractMember?.value = contractMember
            }
        }
    }


}