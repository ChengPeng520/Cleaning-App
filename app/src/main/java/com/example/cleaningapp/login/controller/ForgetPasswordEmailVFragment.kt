package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentForgetPasswordPhoneVBinding
import com.example.cleaningapp.databinding.FragmentRonaForgetPasswordEmailVBinding
import com.example.cleaningapp.login.viewModel.ForgetPasswordEmailVViewModel
import com.example.cleaningapp.login.viewModel.ForgetPasswordPhoneVViewModel

class ForgetPasswordEmailVFragment : Fragment() {
    private lateinit var binding: FragmentRonaForgetPasswordEmailVBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "忘記密碼"
        val viewModel: ForgetPasswordEmailVViewModel by viewModels()
        binding = FragmentRonaForgetPasswordEmailVBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            ivApplyinfoBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            btnForgetPasswordVerify.setOnClickListener {
                if (!inputCheck()){ return@setOnClickListener }
                Navigation.findNavController(it)
                    .navigate(R.id.action_forgetPasswordEmailVFragment_to_resetPasswordFragment)
            }
            tvForgetPasswordResendEmail.setOnClickListener {
                // 再寄送驗證Email
                return@setOnClickListener
            }
        }
    }
    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val email = viewModel?.email?.value?.trim()

            if (email == null || email.isEmpty()) {
                edtTxtForgetPasswordVerifyHint.error = "不可空白"
                check = false
            }
        }
        return check
    }
}