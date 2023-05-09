package com.example.cleaningapp.backstage.usermanage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserVerifyDetailViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsVerifyDetailBinding

class BsUserVerifyDetailFragment : Fragment() {
private lateinit var binding: FragmentAlbBsVerifyDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserVerifyDetailViewModel by viewModels()
        binding = FragmentAlbBsVerifyDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsUserVerifyDetailPass.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyDetailFragment)
            }
            btnBsUserVerifyDetailDecline.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyDeclineFragment)
            }
            ivBsUserVerifyDetailBack.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

}