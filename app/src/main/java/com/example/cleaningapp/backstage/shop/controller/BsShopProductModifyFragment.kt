package com.example.cleaningapp.backstage.shop.controller

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Message
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.backstage.shop.Product
import com.example.cleaningapp.backstage.shop.viewModel.BsShopProductModifyViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductModifyBinding
import com.example.cleaningapp.share.ImageUtils.bitmapToBytes

class BsShopProductModifyFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopProductModifyBinding
    private var imageByteArray: ByteArray? = null
    val viewModel: BsShopProductModifyViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: BsShopProductModifyViewModel by viewModels()
        binding = FragmentAlbBsShopProductModifyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel?.product?.observe(viewLifecycleOwner) {
                switchBsShopProductModify.isChecked = it.isOnSale == true
            }
            btnBsShopProductModifyPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            btnCameraProductModify.setOnClickListener {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureSmallLauncher.launch(intent)
            }

            btnBsShopProductModifySubmit.setOnClickListener {
                //點擊商品更新按鍵直接上傳
                val product = viewModel?.product?.value
                product?.name = edtTxtBsShopModifyName.text.toString()
                product?.description = edtTxtBsShopModifyDescribe.text.toString()
                product?.price = edtTxtBsShopModifyPrice.text.toString().toInt()
                product?.storage = edtTxtBsShopModifyStorage.text.toString().toInt()
                product?.isOnSale = switchBsShopProductModify.isChecked
                if (imageByteArray != null) {
                    product?.photo = imageByteArray
                }

                viewModel?.product?.value = product
                viewModel?.productModify().let { result ->
                    if (result == true) {
                        Toast.makeText(requireContext(), "修改商品成功", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(it).popBackStack()
                    }
                    Toast.makeText(requireContext(), "修改失敗", Toast.LENGTH_SHORT).show()

                }

            }
            arguments?.let { bundle ->
                bundle?.getInt("productId")?.let {
                    viewModel?.fetchProductInfo(it)
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
                        binding.ivBsShopProductModifyPhoto.setImageBitmap(it)
                        imageByteArray = bitmapToBytes(it)
                    }
                }
            }
        }
}

