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
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductBinding

class BsShopOrderFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: BsShopOrderViewModel by viewModels()
        binding = FragmentAlbBsShopOrderBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnBsShopOrderProduct.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.bsShopProductFragment)
            }
        }
    }
}