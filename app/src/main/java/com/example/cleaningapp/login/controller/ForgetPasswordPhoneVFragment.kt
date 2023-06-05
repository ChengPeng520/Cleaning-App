package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentForgetPasswordPhoneVBinding
import com.example.cleaningapp.login.viewModel.ForgetPasswordPhoneVViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.ktx.Firebase

class ForgetPasswordPhoneVFragment : Fragment() {
    private lateinit var binding:FragmentForgetPasswordPhoneVBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "忘記密碼"
        val viewModel: ForgetPasswordPhoneVViewModel   by viewModels()
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
                if (inputCheck()){
                    verifyIdAndCode(verificationId, viewModel?.phoneVerify?.value!!)
                    Navigation.findNavController(it)
                        .navigate(R.id.action_forgetPasswordPhoneVFragment_to_resetPasswordFragment)
                     }
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
            val phone = viewModel?.phoneVerify?.value?.trim()

            if (phone == null || phone.isEmpty()) {
                edtTxtForgetPasswordVerifyHint.error = "不可空白"
                check = false
            }
        }
        return check
    }

    //驗證碼建立設定
    private fun verifyIdAndCode(verificationId: String, verificationCode: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, verificationCode)
        firebaseAuthWithPhoneNumber(credential)
    }

    //驗證碼驗證對錯
    private fun firebaseAuthWithPhoneNumber(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                // 若成功就跳頁
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_forgetPasswordPhoneVFragment_to_resetPasswordFragment)
                } else {
                    // 若使用者輸入的驗證碼與簡訊傳來的不同會產生錯誤
                    val e = task.exception
                    if (e != null) {
                        if (e is FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(context, "驗證碼不正確", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "驗證失敗", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

}