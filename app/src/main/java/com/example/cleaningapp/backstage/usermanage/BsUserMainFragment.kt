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
import com.example.cleaningapp.databinding.FragmentAlbBsShopOrderBinding
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainBinding

class BsUserMainFragment : Fragment() {
private lateinit var binding: FragmentAlbBsUserMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserMainViewModel by viewModels()
        binding = FragmentAlbBsUserMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsUserMainVerify.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyFragment)
            }
            btnBsUserMainSuspend.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }
            btnBsUserMainService.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserServiceFragment)
            }
            btnBsUserMainAdd.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsUserMainAddFragment)
            }
        }
    }
}