package com.example.cleaningapp.backstage.shop.controller


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.Manifest
import com.example.cleaningapp.backstage.shop.viewModel.BsShopProductAddViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductAddBinding


class BsShopProductAddFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopProductAddBinding
    private val REQUEST_CAMERA_PERMISSION = 1001
    private val REQUEST_CAMERA = 1002
    private val REQUEST_GALLERY = 1003
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
            btnCameraProductAdd.setOnClickListener {
                showPictureDialog()
            }
        }
    }

    private fun showPictureDialog() {
        val option = arrayOf("開啟相機", "相簿選取")
        val build = AlertDialog.Builder(requireContext())
        build.setTitle("選擇照片來源")
        build.setItems(option) { _, which ->
            when (which) {
                0 -> checkPermission()
                1 -> openGallery()
            }
        }
        build.create().show()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_GALLERY)
    }

    private fun checkPermission() {
        // 檢查權限並請求相機權限
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // 如果尚未授予相機權限，則請求權限
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        } else {
            openCamera()
        }
    }

    private fun handleCameraResult(data: Intent?) {
        val image = data?.extras?.get("data") as Bitmap
        binding.ivBsShopProductAddPhoto.setImageBitmap(image)
    }
    private fun handleGallery(data: Intent?){
        val imageURL =data?.data
        if (imageURL != null){
            binding.ivBsShopProductAddPhoto.setImageURI(imageURL)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CAMERA -> handleCameraResult(data)
                REQUEST_GALLERY -> handleGallery(data)
            }
        }
    }
}




