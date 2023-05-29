package com.example.cleaningapp.backstage.shop.controller

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.BackstageActivity
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.Product
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
        val viewModel: BsShopProductViewModel by viewModels()

        binding = FragmentAlbBsShopProductBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val product =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getSerializable("product", Product::class.java)
                } else {
                    it.getSerializable("product")
                }
            viewModel.product.value = product as Product
        }

        with(binding) {
            btnBsShopProductDetailPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            btnBsShopProductModify.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.bsShopProductModifyFragment)
            }
            arguments?.let { bundle ->
                bundle.getSerializable("product")?.let {
                    binding.viewModel?.product?.value = it as Product
                }
            }
        }
    }
}

