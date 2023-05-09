package com.example.cleaningapp.backstage.complaint.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.complaint.viewModel.BsCompMainViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsCompMainBinding

class BsCompMainFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsCompMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsCompMainViewModel by viewModels()
        binding = FragmentAlbBsCompMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    with(binding){
        btnBsCompMainDone.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.bsCompDoneFragment)
        }
        }
    }
}