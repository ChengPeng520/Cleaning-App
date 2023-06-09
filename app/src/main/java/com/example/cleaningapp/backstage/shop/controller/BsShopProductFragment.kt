package com.example.cleaningapp.backstage.shop.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.BackstageActivity
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.viewModel.BsShopProductViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductBinding

class BsShopProductFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopProductBinding
    private val viewModel: BsShopProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        (requireActivity() as BackstageActivity).supportActionBar?.show()
        binding = FragmentAlbBsShopProductBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            viewModel?.product?.observe(viewLifecycleOwner) { product ->
                ivBsShopProductPhoto.setImageBitmap(product.photoBitmap)
                tvBsShopCreatetime.text = product.newTimeCreate
                tvBsShopUpdatetime.text = product.newTimeUpdate
            }
            btnBsShopProductDetailPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            btnBsShopProductModify.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsShopProductModifyFragment)
            }
            arguments?.let { bundle ->
                bundle.getInt("productId").let {
                    viewModel?.fetchProductInfo(it)
                }
            }
        }
    }
}