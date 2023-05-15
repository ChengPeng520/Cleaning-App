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
import com.example.cleaningapp.login.viewModel.ForgetPasswordPhoneVViewModel

class ForgetPasswordPhoneVFragment : Fragment() {
    private lateinit var binding:FragmentForgetPasswordPhoneVBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "忘記密碼"
        val viewModel: ForgetPasswordPhoneVViewModel by viewModels()
        binding = FragmentForgetPasswordPhoneVBinding.inflate(inflater,container,false)
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
                    .navigate(R.id.action_forgetPasswordPhoneVFragment_to_resetPasswordFragment)
            }
            tvForgetPasswordResendPhone.setOnClickListener {
                // 再寄送驗證簡訊
                return@setOnClickListener
            }
        }
    }
    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val phone = viewModel?.phone?.value?.trim()

            if (phone == null || phone.isEmpty()) {
                edtTxtForgetPasswordVerifyHint.error = "不可空白"
                check = false
            }
        }
        return check
    }

}