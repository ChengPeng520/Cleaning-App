package com.example.cleaningapp.cleaner.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cleaningapp.CleanerActivity
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.Job
import com.example.cleaningapp.cleaner.viewmodel.search.CleanerViewModel
import com.example.cleaningapp.databinding.FragmentVickyCleanerFrontOrderDetailBinding

class CleanerFrontOrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentVickyCleanerFrontOrderDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 呈現標題列
        (requireActivity() as CleanerActivity).supportActionBar?.show()
        val viewModel: CleanerViewModel by viewModels()
        binding = FragmentVickyCleanerFrontOrderDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("cleaner")?.let {
                binding.viewModel?.cleaner?.value = it as Job
            }
        }
    }
}