package com.example.cleaningapp.login.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaSignupContractBinding
import com.example.cleaningapp.login.viewModel.SignupContractViewModel

class SignupContractFragment : Fragment() {
    private lateinit var binding: FragmentRonaSignupContractBinding
    private var nextPage: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "合約條款"
        val viewModel: SignupContractViewModel by viewModels()
        binding = FragmentRonaSignupContractBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            ivContractBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            //選項比對判斷
//            viewModel?.radioId?.observe(viewLifecycleOwner) {
//                when (viewModel?.radioId?.value) {
//                    R.id.rdoBtn_signup_read -> nextPage =
//                        R.id.action_signupContractFragment_to_memberInfoFragment2
//                    else -> nextPage = R.id.signupContractFragment
//                }
//            }

            //下一步按鈕判斷
            tvSucontractNext.setOnClickListener {

                val bundle = arguments
//                val action = nextPage ?: return@setOnClickListener
//                if (viewModel?.radioId?.value == R.id.rdoBtn_signup_read) {
//                    Navigation.findNavController(it).navigate(action)
//                } else {
//                    Toast.makeText(context, "尚未同意規範", Toast.LENGTH_SHORT).show()
//                }
                if (rdoBtnSignupRead.isChecked) {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_signupContractFragment_to_signupApplyInfoFragment,bundle)
                } else {
                    Toast.makeText(context, "尚未同意規範", Toast.LENGTH_SHORT).show()
                }
            }
            //assets文字內容
            val inputStream = requireContext().assets.open("signup_contract.txt")
            inputStream.bufferedReader().useLines { lines ->
                val contract = lines.fold("") { initial, line ->
                    "$initial$line\n"
                }
                viewModel?.contract?.value = contract
            }

        }
    }
}