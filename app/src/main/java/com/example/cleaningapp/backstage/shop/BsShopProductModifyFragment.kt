package com.example.cleaningapp.backstage.shop

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductBinding
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductModifyBinding

class BsShopProductModifyFragment : Fragment() {
private lateinit var binding: FragmentAlbBsShopProductModifyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsShopProductModifyViewModel by viewModels()
        binding = FragmentAlbBsShopProductModifyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsShopProductModifySubmit.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsShopProductFragment)
            }
            ivBsShopProductModifyBack.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

}