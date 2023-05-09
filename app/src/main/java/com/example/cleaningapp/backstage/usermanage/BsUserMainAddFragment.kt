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
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainAddBinding
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainDetailBinding

class BsUserMainAddFragment : Fragment() {
private lateinit var binding: FragmentAlbBsUserMainAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserMainAddViewModel by viewModels()
        binding = FragmentAlbBsUserMainAddBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsUserMainAddVerify.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyFragment)
            }
            btnBsUserMainAddSuspend.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }
            btnBsUserMainAddService.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserServiceFragment)
            }
            btnBsUserMainSave.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
            }
            ivBsUserMainAddBack.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }

        }
    }
}