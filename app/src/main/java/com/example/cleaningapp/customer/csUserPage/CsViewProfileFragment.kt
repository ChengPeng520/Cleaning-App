package com.example.cleaningapp.customer.csUserPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCsViewProfileBinding

class CsViewProfileFragment : Fragment() {
    private lateinit var binding: FragmentCsViewProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsViewProfileViewModel by viewModels()
        binding = FragmentCsViewProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btnCsProfileModify.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.action_csViewProfileFragment_to_csEditProfileFragment)
            }
        }
    }
}