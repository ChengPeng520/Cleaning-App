package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaSignupBinding
import com.example.cleaningapp.login.viewModel.SignupViewModel

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

            spnSignupStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        nextPage = R.id.action_signupFragment_to_signupContractMemberFragment
                    } else {
                        nextPage = R.id.action_signupFragment_to_signupContractFragment
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            btnSignupSignup.setOnClickListener {
                val action = nextPage ?: return@setOnClickListener //?:是空值的時候
                // some login code

                // login success
                if (!inputCheck()) {
                    return@setOnClickListener
                }
                viewModel?.signup()
                if (!inputCheck()) {
                    return@setOnClickListener
                }
                Navigation.findNavController(it).navigate(action)
            }
        }
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