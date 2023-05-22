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
import com.example.cleaningapp.BackstageActivity
import com.example.cleaningapp.CleanerActivity
import com.example.cleaningapp.CustomerActivity
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.csHomePage.CsHomePageFragment
import com.example.cleaningapp.databinding.FragmentRonaLoginBinding
import com.example.cleaningapp.login.viewModel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentRonaLoginBinding
    private var nextActivity: Intent? = null
    private lateinit var intent: Intent

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
            spnLoginStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
//                    nextActivity = when(getIdentity(position)){
//                        Identity.Customer -> CustomerActivity.getIntent(context)
//                        Identity.Cleaner -> CleanerActivity.getIntent(context)
//                    }
                    intent = if (position == 0) {
                        Intent(requireContext(), CustomerActivity::class.java)
                    } else if (position == 1){
                        Intent(requireContext(), CleanerActivity::class.java)
                    }else {
                        Intent(requireContext(), BackstageActivity::class.java)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            btnLoginLogin.setOnClickListener {
                if (!inputCheck()){
                    return@setOnClickListener
                }
//                this@LoginFragment.startActivity(nextActivity)
                requireContext().startActivity(intent)

                //缺: 如果申請未通過審核, 登入需呈現SignupCheckingFragment

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
