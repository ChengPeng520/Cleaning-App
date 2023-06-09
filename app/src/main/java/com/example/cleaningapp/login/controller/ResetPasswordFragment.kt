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
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaResetPasswordBinding
import com.example.cleaningapp.login.viewModel.ResetPasswordViewModel
import com.example.cleaningapp.share.Constants
import com.example.cleaningapp.share.requestTask
import com.google.gson.JsonObject

class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentRonaResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: ResetPasswordViewModel by viewModels()
        binding = FragmentRonaResetPasswordBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<Toolbar>(R.id.login_toolbar).visibility = View.VISIBLE
        requireActivity().findViewById<TextView>(R.id.login_toolbar_title).text = "重設密碼"
        with(binding) {
            btnResetPasswordConfirm.setOnClickListener { view ->
                if (!inputCheck()) {
                    return@setOnClickListener
                }
                // 手機號碼(用手機密碼拿到資料)跟密碼帶到後端修改 edit(toDelete)
                // 連到AccountCustomer doDelete
                resetPassword()?.let {
//                    SignupFragment().saveEncryptedPassword()
                    Navigation.findNavController(view)
                        .navigate(R.id.action_resetPasswordFragment_to_loginFragment)
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val password = viewModel?.password?.value
            val cPassword = viewModel?.cPassword?.value

            if ((password == null) || password.isEmpty()) {
                edtTxtResetPasswordNP.error = "不可空白"
                check = false
            }

            if (cPassword != password) {
                edtTxtResetPasswordCheckNP.error = "密碼不一致" // 抓不到string的值, 先以字串代替
                check = false
            }
            return check
        }
    }

    fun resetPassword(): Boolean {
        // bundle手機號碼帶到這頁
        val phoneNumber = arguments?.getString("phoneNumber")
        Log.d("phone","phone: $phoneNumber")
        val url = "${Constants.BASE_URL}/AccountCustomer/"
        requestTask<JsonObject>(
            "$url${phoneNumber}/${binding.viewModel?.password?.value}",
            "DELETE"
        )?.let {
            if (it.get("result").asBoolean) {
                Toast.makeText(context, "重設成功", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "重設失敗", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }
}