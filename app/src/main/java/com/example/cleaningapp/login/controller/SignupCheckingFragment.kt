package com.example.cleaningapp.login.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaSignupCheckingBinding
import com.example.cleaningapp.login.viewModel.SignupCheckingViewModel

class SignupCheckingFragment : Fragment() {
    private lateinit var binding: FragmentRonaSignupCheckingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: SignupCheckingViewModel by viewModels()
        binding = FragmentRonaSignupCheckingBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            tvSignupBtnBack.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_signupCheckingFragment_to_loginFragment)
            }
        }
    }
}