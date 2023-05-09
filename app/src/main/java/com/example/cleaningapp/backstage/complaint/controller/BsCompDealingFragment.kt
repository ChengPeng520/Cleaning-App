package com.example.cleaningapp.backstage.complaint.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.complaint.viewModel.BsCompDealingViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsCompDealingBinding

class BsCompDealingFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsCompDealingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsCompDealingViewModel by viewModels()
        binding = FragmentAlbBsCompDealingBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsCompDealingAgree.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsCompDetailFragment)
            }

            btnBsCompDealingDecline.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsCompDoneFragment)
            }
            ivBsCompDealingBack.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsCompMainFragment)
            }
        }
    }
}