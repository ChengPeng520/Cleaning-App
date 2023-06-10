package com.example.cleaningapp.backstage.usermanage.controller

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserMainAddViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainAddBinding

class BsUserMainAddFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserMainAddBinding
    val viewModel: BsUserMainAddViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbBsUserMainAddBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnBsUserMainAddVerify.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyFragment)
            }
            btnBsUserMainAddSuspend.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }
            btnBsUserMainAddService.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserServiceFragment)
            }
            btnBsUserMainSave.setOnClickListener {
                if (!inputCheck()) {
                    return@setOnClickListener
                }
                //TODO error: lateinit property sharedPreferences has not been initialized
                viewModel?.backstageRegister()?.let {
                    if (it) {
                        Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
                    } else {
                        Toast.makeText(context, "註冊失敗", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            ivBsUserMainAddBack.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val account = viewModel?.account?.value?.trim()
            val password = viewModel?.password?.value?.trim()
            val fullName = viewModel?.fullName?.value?.trim()

            if (fullName == null || fullName.isEmpty()) {
                Toast.makeText(context, "姓名不可空白", Toast.LENGTH_SHORT).show()
                check = false
            } else if (account == null || account.isEmpty()) {
                Toast.makeText(context, "帳號不可空白", Toast.LENGTH_SHORT).show()
                check = false
            } else if (password == null || password.isEmpty()) {
                Toast.makeText(context, "密碼不可空白", Toast.LENGTH_SHORT).show()
                check = false
            }
            return check
        }
    }
}