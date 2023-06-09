package com.example.cleaningapp.login.controller

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cleaningapp.R

class SignupCheckApplyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val signupCheckApplyFragment =
            inflater.inflate(R.layout.fragment_rona_signup_check_apply, container, false)
        val countDownTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                Navigation.findNavController(signupCheckApplyFragment)
                    .navigate(R.id.action_signupCheckApplyFragment_to_loginFragment)
            }
        }
        countDownTimer.start()
        return signupCheckApplyFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<Toolbar>(R.id.login_toolbar).visibility = View.VISIBLE
        requireActivity().findViewById<TextView>(R.id.login_toolbar_title).text = ""
    }
}