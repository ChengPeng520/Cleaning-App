package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentForgetPasswordPhoneVBinding
import com.example.cleaningapp.login.viewModel.ForgetPasswordPhoneVViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class ForgetPasswordPhoneVFragment : Fragment() {
    private lateinit var binding: FragmentForgetPasswordPhoneVBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private val myTag = "TAG_${javaClass.simpleName}"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: ForgetPasswordPhoneVViewModel by viewModels()
        binding = FragmentForgetPasswordPhoneVBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<Toolbar>(R.id.login_toolbar).visibility = View.VISIBLE
        requireActivity().findViewById<TextView>(R.id.login_toolbar_title).text = "忘記密碼"
        with(binding) {
            ivApplyinfoBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            btnForgetPasswordVerify.setOnClickListener {
                if (inputCheck()) {
                    verificationId = arguments?.getString("verificationId").toString()
                    verifyIdAndCode(verificationId, viewModel?.phoneVerify?.value!!)
                }
            }

            tvForgetPasswordResendPhone.setOnClickListener {
                val phoneNumber: String? = arguments?.getString("phoneNumber")
                if (phoneNumber != null) {
                    resendVerificationCode(phoneNumber, resendToken)
                }
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
                // 若驗證碼比對成功就跳頁
                if (task.isSuccessful) {
                    val bundle = arguments
                    findNavController().navigate(R.id.action_forgetPasswordPhoneVFragment_to_resetPasswordFragment, bundle)
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

    private fun resendVerificationCode(
        phone: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        val phoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(verifyCallbacks)
            /* 驗證碼發送後，verifyCallbacks.onCodeSent()會傳來token，
               使用者要求重傳驗證碼必須提供token */
            .setForceResendingToken(token)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    private val verifyCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            /** This callback will be invoked in two situations:
             * 1 - Instant verification. In some cases the phone number can be instantly
             * verified without needing to send or enter a verification code.
             * 2 - Auto-retrieval. On some devices Google Play services can automatically
             * detect the incoming verification SMS and perform verification without
             * user action.  */
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(myTag, "onVerificationCompleted: $credential")
            }

            /**
             * 發送驗證碼填入的電話號碼格式錯誤，或是使用模擬器發送都會產生發送錯誤，
             * 使用模擬器發送會產生下列執行錯誤訊息：
             * App validation failed. Is app running on a physical device?
             */
            override fun onVerificationFailed(e: FirebaseException) {
                Log.e(myTag, "onVerificationFailed: ${e.message}")
            }

            /**
             * The SMS verification code has been sent to the provided phone number,
             * we now need to ask the user to enter the code and then construct a credential
             * by combining the code with a verification ID.
             */
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(myTag, "onCodeSent: $id")
                verificationId = id
                resendToken = token
            }
        }
}

