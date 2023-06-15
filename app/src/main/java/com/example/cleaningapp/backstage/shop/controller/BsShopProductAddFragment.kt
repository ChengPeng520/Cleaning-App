package com.example.cleaningapp.backstage.shop.controller

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.backstage.shop.BsShopProductViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductAddBinding
import com.example.cleaningapp.share.ImageUtils.bitmapToBytes

class BsShopProductAddFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopProductAddBinding
    val viewModel: BsShopProductViewModel by viewModels()
    private var imageByteArray: ByteArray? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAlbBsShopProductAddBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnBsShopProductAddPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            btnCameraProductAdd.setOnClickListener {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureSmallLauncher.launch(intent)
            }

            btnProductAdd.setOnClickListener {
                val product = viewModel?.product?.value
                product?.name = edtTxtBsShopProductAddName.text.toString()
                product?.description = editBsShopProductAddDescribe.text.toString()
                product?.price = edtTxtBsShopProductAddPrice.text.toString().toInt()
                product?.storage = edtTxtBsShopProductAddStorage.text.toString().toInt()
                product?.isOnSale = switchBsShopProductAdd.isChecked
                product?.photo = imageByteArray

                viewModel?.product?.value = product
                viewModel?.productAdd().let { result ->
                    if (result == true) {
                        Toast.makeText(requireContext(), "新增商品成功", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view).popBackStack()
                    }
                    Toast.makeText(requireContext(), "新增失敗", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private var takePictureSmallLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras?.let { bundle ->
                    val picture = bundle["data"] as Bitmap?
                    picture?.let {
                        binding.ivBsShopProductAddPhoto.setImageBitmap(it)
                        imageByteArray = bitmapToBytes(it)
                    }

                }
            }
        }
}