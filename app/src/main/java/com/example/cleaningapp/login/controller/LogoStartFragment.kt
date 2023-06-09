package com.example.cleaningapp.login.controller

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cleaningapp.R

class LogoStartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val logoStartFragment = inflater.inflate(R.layout.fragment_logo_start, container, false)
        val countDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
//                val loginFragment = LoginFragment()
//                val transaction = childFragmentManager.beginTransaction()
//                transaction.replace(R.id.fragment_logo_start, loginFragment).commit()
                Navigation.findNavController(logoStartFragment)
                    .navigate(R.id.action_logoStartFragment_to_loginFragment)

            }
        }
        countDownTimer.start()
        return logoStartFragment
    }
}