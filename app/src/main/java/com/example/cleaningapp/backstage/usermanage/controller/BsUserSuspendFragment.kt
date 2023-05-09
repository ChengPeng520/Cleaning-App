package com.example.cleaningapp.backstage.usermanage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserSuspendViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserSuspendBinding

class BsUserSuspendFragment : Fragment() {
private lateinit var binding: FragmentAlbBsUserSuspendBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserSuspendViewModel by viewModels()
        binding = FragmentAlbBsUserSuspendBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsUserSuspUser.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
            }
            btnBsUserSuspVerify.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyFragment)
            }
            btnBsUserSuspService.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserServiceFragment)
            }

        }
    }
}