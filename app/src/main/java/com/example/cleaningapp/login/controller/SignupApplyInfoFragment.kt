package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaResetPasswordBinding
import com.example.cleaningapp.databinding.FragmentRonaSignupApplyInfoBinding
import com.example.cleaningapp.login.viewModel.SignupApplyInfoViewModel

class SignupApplyInfoFragment : Fragment() {
    private lateinit var binding: FragmentRonaSignupApplyInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "申請資料"
        val viewModel: SignupApplyInfoViewModel by viewModels()
        binding = FragmentRonaSignupApplyInfoBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            ivSuInfoBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            tvSuInfoDone.setOnClickListener {
                if (!inputCheck()){ return@setOnClickListener }
                Navigation.findNavController(it).navigate(R.id.action_signupApplyInfoFragment_to_signupCheckApplyFragment)
            }
        }
    }
    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val fullName = viewModel?.fullName?.value?.trim()
            val phone = viewModel?.phone?.value?.trim()

            if (fullName == null || fullName.isEmpty() || phone == null || phone.isEmpty()) {
                Toast.makeText(context, "姓名或手機不可空白", Toast.LENGTH_SHORT).show()
                check = false
            }

//            val id1 = viewModel?.id1?.value
//            val id2 = viewModel?.id2?.value
//            val goodPerson = viewModel?.goodPerson?.value
//
//            if ( id1 == null || id2 == null || goodPerson == null){
//                Toast.makeText(context, "請上傳照片", Toast.LENGTH_SHORT).show()
//                check = false
//            }
            return check
        }
    }

}