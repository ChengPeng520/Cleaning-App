package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaSignupBinding
import com.example.cleaningapp.login.viewModel.SignupViewModel
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentRonaSignupBinding
    private var nextPage: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "會員註冊"
        val viewModel: SignupViewModel by viewModels()
        binding = FragmentRonaSignupBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            tvSignupCancel.setOnClickListener() {
                Navigation.findNavController(it).popBackStack()
            }

            val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SuspiciousIndentation")
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            btnSignupSignup.setOnClickListener { view ->
                if (!inputCheck()) {
                    return@setOnClickListener
                }
                spnSignupStatus.onItemSelectedListener = onItemSelectedListener
                val position = spnSignupStatus.selectedItemPosition

                if (position == 0) {
                    val url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountCustomer/"
                    requestTask<JsonObject>("$url/${viewModel?.account}")?.let {
                        if (it.get("result").asBoolean){
                        tvSignupErrMsg.text = "此帳號已存在"

                        }else{
                            saveEncryptedPassword()
                            val bundle = Bundle()
                            bundle.putString("emailAccount", viewModel?.account?.value)
                            bundle.putString("password", viewModel?.password?.value)
                            findNavController().navigate(R.id.action_signupFragment_to_signupContractMemberFragment, bundle)
                        }
                    }

                } else {
                    val url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountCleaner/"
                    requestTask<JsonObject>("$url/${viewModel?.account}")?.let {
                        Log.d("result", it.get("result").asBoolean.toString())
                        if (it.get("result").asBoolean) {
                            tvSignupErrMsg.text = "此帳號已存在"
                        } else {
                            saveEncryptedPassword()
                            val bundle = Bundle()
                            bundle.putString("emailAccount", viewModel?.account?.value)
                            bundle.putString("password", viewModel?.password?.value)
                            findNavController().navigate(R.id.action_signupFragment_to_signupContractFragment, bundle)
                        }
                    }
                }
            }
        }
    }

    fun saveEncryptedPassword() {
        with(binding) {
            getEncryptedPassword().edit()
                .putString("password", viewModel?.password?.value)
                .apply()
        }
    }

    fun getEncryptedPassword(): SharedPreferences {
        val masterKeyAlias = MasterKey.Builder(requireContext())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            requireContext(),
            "encryptedPassword",
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val regex =
                Regex("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
            val account = viewModel?.account?.value?.trim()
            val password = viewModel?.password?.value?.trim()
            val cPassword = viewModel?.cPassword?.value?.trim()

            if (account == null || account.isEmpty() || password == null || password.isEmpty()) {
                tvSignupErrMsg.text = "帳號或密碼不可為空白"
                check = false
            } else if (account.matches(regex)) {
                tvSignupErrMsg.text = ""
            } else {
                tvSignupErrMsg.text = ""
                edtTxtSignupAccount.error = "帳號格式錯誤" // 抓不到string的值, 先以字串代替
                check = false
            }

            if (cPassword != password) {
                tvSignupErrMsg.text = ""
                edtTxtSignupPasswordcfm.error = "密碼不一致" // 抓不到string的值, 先以字串代替
            }
            return check
        }
    }
}


public enum class Identity { //用陣列約束
    Customer, Cleaner
}

fun getIdentity(number: Int): Identity {
    return when (number) {
        0 -> Identity.Customer
        1 -> Identity.Cleaner
        else -> throw Exception()
    }
}