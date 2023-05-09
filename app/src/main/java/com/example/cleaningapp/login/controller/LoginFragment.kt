package com.example.cleaningapp.login.controller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.csHomePage.CsHomePageFragment
import com.example.cleaningapp.databinding.FragmentRonaLoginBinding
import com.example.cleaningapp.login.viewModel.LoginViewModel

class LoginFragment : Fragment() {
private lateinit var binding: FragmentRonaLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: LoginViewModel by viewModels()
        binding = FragmentRonaLoginBinding.inflate(inflater, container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding){

            btnLoginLogin.setOnClickListener {
                val intent = Intent(context, CsHomePageFragment::class.java)
                startActivity(intent)
            }

            tvLoginSignUp.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_signupFragment)
            }
        }
    }


}
