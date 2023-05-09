package com.example.cleaningapp.backstage.usermanage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserVerifyViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserVerifyBinding

class BsUserVerifyFragment : Fragment() {
private lateinit var binding: FragmentAlbBsUserVerifyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: BsUserVerifyViewModel by viewModels()
        binding = FragmentAlbBsUserVerifyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsUserVerifyUser.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
            }
            btnBsUserVerifySuspend.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }
            btnBsUserVerifyService.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserServiceFragment)
            }
        }
    }
}