package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.cleaningapp.BackstageActivity
import com.example.cleaningapp.CleanerActivity
import com.example.cleaningapp.CustomerActivity
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.AccountBackstage
import com.example.cleaningapp.databinding.FragmentRonaLoginBinding
import com.example.cleaningapp.login.viewModel.LoginViewModel
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentRonaLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "會員登入"
        val viewModel: LoginViewModel by viewModels()
        binding = FragmentRonaLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            val onItemSelected = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            btnLoginLogin.setOnClickListener { view ->
                if (!inputCheck()) {
                    return@setOnClickListener
                }
                spnLoginStatus.onItemSelectedListener = onItemSelected
                val position = spnLoginStatus.selectedItemPosition

                if (position == 0) {
                    val url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountCustomer/"
                    requestTask<CustomerSharePreferencesUtils.ApiCustomerModel>(
                        "$url${viewModel?.account?.value}/${viewModel?.password?.value}"
                    )?.let {
                        if (it.suspend) {
                            tvLoginErrMsg.text = "此帳號已停權"
                        } else {
                            val intent = Intent(requireContext(), CustomerActivity::class.java)
                            requireContext().startActivity(intent)
                        }
                        return@setOnClickListener
                    }
                    tvLoginErrMsg.text = "使用者帳號或密碼不正確"
                } else if (position == 1) {
                    val url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountCleaner/"
                    requestTask<CleanerSharedPreferencesUtils.ApiCleanerModel>(
                        "$url${viewModel?.account?.value}/${viewModel?.password?.value}"
                    )?.let {
                        if (it.suspend) {
                            tvLoginErrMsg.text = "此帳號已停權"
                        } else if (!it.verify) {
                            findNavController().navigate(R.id.action_loginFragment_to_signupCheckingFragment)
                        } else {
                            val intent = Intent(requireContext(), CleanerActivity::class.java)
                            requireContext().startActivity(intent)
                        }
                        return@setOnClickListener
                    }
                    tvLoginErrMsg.text = "使用者帳號或密碼不正確"
                } else {
                    val url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/"
                    requestTask<AccountBackstage>(
                        "$url${viewModel?.account?.value}/${viewModel?.password?.value}"
                    )?.let {
                        if (it.suspend) {
                            tvLoginErrMsg.text = "此帳號已停權"
                        } else {
                            val intent = Intent(requireContext(), BackstageActivity::class.java)
                            requireContext().startActivity(intent)
                        }
                        return@setOnClickListener
                    }
                    tvLoginErrMsg.text = "使用者帳號或密碼不正確"
                }
            }
            tvLoginSignUp.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_signupFragment)
            }

            tvLoginForgetPassword.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_loginForgetPasswordPhoneFragment)
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val regex =
                Regex("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+\$")
            val account = viewModel?.account?.value?.trim()
            val password = viewModel?.password?.value?.trim()
            if (account == null || account.isEmpty() || password == null || password.isEmpty()) {
                tvLoginErrMsg.text = "帳號或密碼不可為空白" // 抓不到string的值, 先以字串代替
                check = false
            } else if (account.matches(regex)) {
                tvLoginErrMsg.text = ""
            } else {
                tvLoginErrMsg.text = ""
                edtTxtLoginAccount.error = "帳號或密碼錯誤" // 抓不到string的值, 先以字串代替
                check = false
            }
            return check
        }
    }
}
