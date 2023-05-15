package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaSignupApplyInfoBinding
import com.example.cleaningapp.databinding.FragmentRonaSignupContractMemberBinding
import com.example.cleaningapp.databinding.FragmentSignupMemberInfoBinding
import com.example.cleaningapp.login.viewModel.SignupApplyInfoViewModel
import com.example.cleaningapp.login.viewModel.SignupMemberInfoViewModel

class signupMemberInfoFragment : Fragment() {
    private lateinit var binding: FragmentSignupMemberInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "會員資料"
        val viewModel: SignupMemberInfoViewModel by viewModels()
        binding = FragmentSignupMemberInfoBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            ivMemInfoBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            tvMemInfoDone.setOnClickListener {
                if (!inputCheck()){ return@setOnClickListener }
                Navigation.findNavController(it).navigate(R.id.action_signupMemberInfoFragment_to_loginFragment)
            }
        }
    }
    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val fullName = viewModel?.fullName?.value?.trim()
            val phone = viewModel?.phone?.value?.trim()

            if (fullName == null || fullName.isEmpty() || phone == null || phone.isEmpty()) {
                Toast.makeText(context, "姓名或手機不可空白", Toast.LENGTH_SHORT).show()
                check = false
            }
            return check
        }
    }


}