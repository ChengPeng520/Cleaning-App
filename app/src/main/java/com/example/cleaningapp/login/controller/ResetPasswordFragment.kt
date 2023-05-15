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
import com.example.cleaningapp.databinding.FragmentRonaResetPasswordBinding
import com.example.cleaningapp.login.viewModel.ResetPasswordViewModel

class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentRonaResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "重設密碼"
        val viewModel: ResetPasswordViewModel by viewModels()
        binding = FragmentRonaResetPasswordBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            btnResetPasswordConfirm.setOnClickListener {
                if (!inputCheck()){
                    return@setOnClickListener
                }
                Navigation.findNavController(it).navigate(R.id.action_resetPasswordFragment_to_loginFragment)
            }
        }
    }
    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val password = viewModel?.password?.value?.trim()
            val cPassword = viewModel?.cPassword?.value?.trim()

            if (password == null || password.isEmpty()) {
                edtTxtResetPasswordNP.error = "不可空白"
                check = false
            }

            if ( cPassword != password){
                edtTxtResetPasswordCheckNP.error = "密碼不一致" // 抓不到string的值, 先以字串代替
                check = false
            }
            return check
        }
    }

}