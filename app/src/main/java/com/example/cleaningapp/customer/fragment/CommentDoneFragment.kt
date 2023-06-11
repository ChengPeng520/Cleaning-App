package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.CommentDoneViewModel
import com.example.cleaningapp.databinding.FragmentVictorCommentDoneBinding

class CommentDoneFragment : Fragment() {
    private lateinit var binding: FragmentVictorCommentDoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CommentDoneViewModel by viewModels()
        binding = FragmentVictorCommentDoneBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val handler = Handler()
        handler.postDelayed({
            Navigation.findNavController(view).navigate(R.id.historicalorderFragment)
        }, 1000)
    }
}