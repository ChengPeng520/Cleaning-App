package com.example.cleaningapp.backstage.usermanage.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserVerifyDeclineViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserVerifyDeclineBinding

class BsUserVerifyDeclineFragment : Fragment() {
private lateinit var binding: FragmentAlbBsUserVerifyDeclineBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserVerifyDeclineViewModel by viewModels()
        binding = FragmentAlbBsUserVerifyDeclineBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsUserVerifyDeclineSubmit.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyFragment)
            }
            ivBsUserVerifyDeclineBack.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }
        }
    }
}