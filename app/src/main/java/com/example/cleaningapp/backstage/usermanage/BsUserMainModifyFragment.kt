package com.example.cleaningapp.backstage.usermanage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainDetailBinding
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainModifyBinding

class BsUserMainModifyFragment : Fragment() {
private lateinit var binding: FragmentAlbBsUserMainModifyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserMainModifyViewModel by viewModels()
        binding = FragmentAlbBsUserMainModifyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsUserMainModifySubmit.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserMainDetailFragment)
            }
            ivBsUserMainModifyBack.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
        }
    }
}