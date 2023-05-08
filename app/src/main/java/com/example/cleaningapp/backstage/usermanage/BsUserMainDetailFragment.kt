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
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainBinding
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainDetailBinding

class BsUserMainDetailFragment : Fragment() {
private lateinit var binding: FragmentAlbBsUserMainDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserMainDetailViewModel by viewModels()
        binding = FragmentAlbBsUserMainDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsUserMainDetailModify.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserMainModifyFragment)
            }
            btnBsUserMainDetailDelete.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
            }
            ivBsUserMainDetailBack.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }
        }
    }
}