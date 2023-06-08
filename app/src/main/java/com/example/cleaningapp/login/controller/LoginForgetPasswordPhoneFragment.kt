package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaLoginForgetPasswordPhoneBinding
import com.example.cleaningapp.login.viewModel.LoginForgetPasswordPhoneViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class  LoginForgetPasswordPhoneFragment : Fragment() {
    private lateinit var binding: FragmentRonaLoginForgetPasswordPhoneBinding
    private val myTag = "TAG_${javaClass.simpleName}"
    private lateinit var auth: FirebaseAuth
    private var verificationId: String = ""
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

            btnForgetPasswordSend.setOnClickListener {
                //1.發送驗證需求給手機
                //2.轉跳至驗證頁面
                if (inputCheck()){
                    val phone = viewModel?.phone?.value?.substring(1)
                    requestVerificationCode("+886$phone")
                    if(verificationId.isNotEmpty()){
                        val bundle = Bundle()
                        bundle.putString("phoneNumber", viewModel?.phone?.value)
                        bundle.putString("verificationId", verificationId)
                        Log.d(myTag, "verificationId: $verificationId")
                        Navigation.findNavController(it)
                            .navigate(R.id.action_loginForgetPasswordPhoneFragment_to_forgetPasswordPhoneVFragment,bundle)
                    }else{
                        return@setOnClickListener
                    }
                }
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

    // 發送驗證需求給手機
    private fun requestVerificationCode(phone: String) {
        auth.setLanguageCode("zh-Hant")
        val phoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
            // 輸入的電話號碼
            .setPhoneNumber(phone)
            // 驗證碼失效時間，設為60秒代表即使多次請求驗證碼，過了60秒才會發送第2次
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            // 監控電話驗證的狀態
            .setCallbacks(verifyCallbacks)
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

