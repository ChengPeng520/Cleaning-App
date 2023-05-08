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
import com.example.cleaningapp.databinding.FragmentAlbBsUserServiceBinding

class BsUserServiceFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsUserServiceViewModel by viewModels()
        binding = FragmentAlbBsUserServiceBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnBsUserServUser.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserMainFragment)
            }
            btnBsUserServVerify.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserVerifyFragment)
            }
            btnBsUserServSuspend.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }

        }
    }
}