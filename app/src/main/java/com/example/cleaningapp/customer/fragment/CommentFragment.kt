package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.customer.viewModel.CommentViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentVictorCommentBinding

class CommentFragment : Fragment() {
    private lateinit var binding: FragmentVictorCommentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CommentViewModel by viewModels()
        binding = FragmentVictorCommentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnCommentOk.setOnClickListener {
                Navigation.findNavController(btnCommentOk)
                    .navigate(R.id.action_commentFragment_to_commentDoneFragment)
            }
        }
    }
}