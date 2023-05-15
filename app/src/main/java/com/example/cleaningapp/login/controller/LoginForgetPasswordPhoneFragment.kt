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
import com.example.cleaningapp.databinding.FragmentRonaLoginForgetPasswordPhoneBinding
import com.example.cleaningapp.login.viewModel.LoginForgetPasswordPhoneViewModel

class LoginForgetPasswordPhoneFragment : Fragment() {
    private lateinit var binding: FragmentRonaLoginForgetPasswordPhoneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "忘記密碼"
        val viewModel: LoginForgetPasswordPhoneViewModel by viewModels()
        binding = FragmentRonaLoginForgetPasswordPhoneBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            ivApplyinfoBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            tvForgetPasswordUseEmail.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginForgetPasswordPhoneFragment_to_loginForgetPasswordEmailFragment)
            }
            btnForgetPasswordSend.setOnClickListener {
                //1.手機寄送簡訊驗證通知
                //2.轉跳至驗證頁面
                if (!inputCheck()){
                    return@setOnClickListener
                }
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginForgetPasswordPhoneFragment_to_forgetPasswordPhoneVFragment)
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val phone = viewModel?.phone?.value?.trim()

            if (phone == null || phone.isEmpty()) {
                edtTxtForgetPasswordPhoneHint.error = "不可空白"
                check = false
            }
        }
            return check
    }
}

