package com.example.cleaningapp.backstage.shop.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.BsShopProductViewModel
import com.example.cleaningapp.backstage.shop.Product
import com.example.cleaningapp.backstage.shop.viewModel.BsShopProductModifyViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductModifyBinding

class BsShopProductModifyFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopProductModifyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel:com.example.cleaningapp.backstage.shop.viewModel.BsShopProductViewModel by viewModels()
        binding = FragmentAlbBsShopProductModifyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnCameraProductModify.setOnClickListener {

            }
            btnBsShopProductModifySubmit.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
            arguments?.let { bundle ->
                bundle?.getSerializable("product")?.let {
                    binding.viewModel?.product?.value = it as Product
                }
            }


        }
    }
}

