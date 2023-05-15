package com.example.cleaningapp.customer.csUserPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCsEditProfileBinding

class CsEditProfileFragment : Fragment() {
    private lateinit var binding:FragmentCsEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsEditProfileViewModel by viewModels()
        binding = FragmentCsEditProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btnCsEditProfileRestore.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.action_csEditProfileFragment_to_csProfileFragment)
            }
        }
    }


}