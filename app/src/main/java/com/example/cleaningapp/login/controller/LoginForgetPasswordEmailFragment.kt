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
import com.example.cleaningapp.databinding.FragmentRonaLoginForgetPasswordEmailBinding
import com.example.cleaningapp.login.viewModel.ForgetPasswordPhoneVViewModel
import com.example.cleaningapp.login.viewModel.LoginForgetPasswordEmailViewModel

class LoginForgetPasswordEmailFragment : Fragment() {
    private lateinit var binding: FragmentRonaLoginForgetPasswordEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "忘記密碼"
        val viewModel: LoginForgetPasswordEmailViewModel by viewModels()
        binding = FragmentRonaLoginForgetPasswordEmailBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            ivApplyinfoBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            tvForgetPasswordUsePhone.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            btnForgetPasswordSend.setOnClickListener {
                //Email寄送驗證通知

                if (!inputCheck()){ return@setOnClickListener }
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginForgetPasswordEmailFragment_to_forgetPasswordEmailVFragment)
            }
        }
    }
    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val email = viewModel?.email?.value?.trim()

            if (email == null || email.isEmpty()) {
                edtTxtForgetPasswordEmailHint.error = "不可空白"
                check = false
            }
        }
        return check
    }
}