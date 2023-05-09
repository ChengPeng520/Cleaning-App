package com.example.cleaningapp.backstage.complaint.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.complaint.viewModel.BsCompDoneViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsCompDoneBinding

class BsCompDoneFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsCompDoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsCompDoneViewModel by viewModels()
        binding = FragmentAlbBsCompDoneBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsCompDoneDealing.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsCompMainFragment)
            }
        }
    }

}