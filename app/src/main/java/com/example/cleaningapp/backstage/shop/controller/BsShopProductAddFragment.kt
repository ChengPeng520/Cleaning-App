package com.example.cleaningapp.backstage.shop.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.viewModel.BsShopProductAddViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductAddBinding

class BsShopProductAddFragment : Fragment() {
private lateinit var binding: FragmentAlbBsShopProductAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsShopProductAddViewModel by viewModels()
        binding = FragmentAlbBsShopProductAddBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsShopProductAddSubmit.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.bsShopProductFragment)
            }
            ivBsShopProductAddBack.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

}