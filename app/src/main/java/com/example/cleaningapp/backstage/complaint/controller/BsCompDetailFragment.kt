package com.example.cleaningapp.backstage.complaint.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.complaint.model.BSCompOrderItem
import com.example.cleaningapp.backstage.complaint.model.Complaint
import com.example.cleaningapp.backstage.complaint.viewModel.BsCompDetailViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsCompDetailBinding

class BsCompDetailFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsCompDetailBinding
    val viewModel: BsCompDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAlbBsCompDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            bundle.getInt("orderIdDone").let {
                viewModel.loadComplaint(it)
            }
            bundle.getInt("orderIdDealing").let {
                viewModel.loadComplaint(it)
            }
        }
        with(binding) {
            ivBsCompDetailBack.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
        }
    }
}