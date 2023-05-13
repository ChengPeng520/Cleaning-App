package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.CsUserViewModel
import com.example.cleaningapp.databinding.FragmentCsUserBinding

class CsUserFragment : Fragment() {
    private lateinit var binding:FragmentCsUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsUserViewModel by viewModels()
        binding = FragmentCsUserBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            Navigation.findNavController(binding.btnCsUserPageProfile).navigate(R.id.action_csUserFragment_to_csProfileFragment)
            Navigation.findNavController(binding.btnCsUserPageDiscount).navigate(R.id.action_csUserFragment_to_csViewCouponFragment)
            Navigation.findNavController(binding.btnCsUserPageLogOut).navigate(R.id.action_csUserFragment_to_loginFragment2)
        }
    }


}