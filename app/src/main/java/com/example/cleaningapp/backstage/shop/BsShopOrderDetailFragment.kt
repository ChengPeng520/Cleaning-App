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
import com.example.cleaningapp.databinding.FragmentAlbBsShopOrderBinding
import com.example.cleaningapp.databinding.FragmentAlbBsShopOrderDetailBinding

class BsShopOrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopOrderDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsShopOrderDetailViewModel by viewModels()
        binding = FragmentAlbBsShopOrderDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsShopOrderDetailShip.setOnClickListener{

            }
            ivBsShopOrderDetailBack.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }
        }
    }
}