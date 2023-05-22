package com.example.cleaningapp.backstage.shop.controller

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.viewModel.BsShopProductAddViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductAddBinding
import java.io.File

class BsShopProductAddFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopProductAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: BsShopProductAddViewModel by viewModels()
        binding = FragmentAlbBsShopProductAddBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnProductAdd.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
//            btnCameraProductAdd.setOnClickListener{
//
//            }
        }
    }


//    private val takePictureSmallLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            result.data?.extras?.let { bundle ->
//                with(binding) {
//                    val picture = bundle.get("data") as Bitmap
//                    if (picture != null) {
//                        ivBsShopProductAddPhoto.setImageBitmap(picture)
//                    }
//                }
//            }
//        }
//    }






//    private val takePictureSmallLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                result.data?.extras?.let { bundle ->
//                    with(binding) {
//                        val picture = bundle.get("data") as Bitmap
//                        if (picture != null) {
//                            ivBsShopProductAddPhoto.setImageBitmap(picture)
//                        }
//                    }
//                }
//            }
//        }
}




