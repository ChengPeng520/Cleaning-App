package com.example.cleaningapp.backstage.shop.controller

import android.Manifest
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
import android.widget.ImageView
import android.widget.Toast
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
    private val REQUEST_CARMERA_PERMISSION = 1001
    private val REQUEST_CAMERA = 1002
    private val REQUEST_GALLERY = 1003
    private var imageByteArray: ByteArray? = null
    val viewModel: BsShopProductModifyViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel:BsShopProductModifyViewModel by viewModels()
        binding = FragmentAlbBsShopProductModifyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel?.product?.observe(viewLifecycleOwner){
                switchBsShopProductModify.isChecked = it.isOnSale == true
            }
            btnBsShopProductModifyPopback.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            btnCameraProductModify.setOnClickListener {
                showDialogChoseCameraOrGallery()
            }
            btnBsShopProductModifySubmit.setOnClickListener {
                //點擊商品更新按鍵直接上傳
                val product = viewModel?.product?.value
                product?.name = edtTxtBsShopModifyName.text.toString()
                product?.description =edtTxtBsShopModifyDescribe.text.toString()
                product?.price =edtTxtBsShopModifyPrice.text.toString().toInt()
                product?.storage =edtTxtBsShopModifyStorage.text.toString().toInt()
                product?.isOnSale = switchBsShopProductModify.isChecked
                if (imageByteArray != null){
                    product?.photo = imageByteArray
                }

                viewModel?.product?.value = product
                viewModel?. productModify().let {result ->
                if (result == true){
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
    //設定點擊相機按鈕跳出dialog選擇相機或相簿
    private fun showDialogChoseCameraOrGallery() {
        //宣告變數定義兩個選項
        val option = arrayOf("開啟相機", "開啟相簿")
        //建立本頁內容的AlertDialog
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("選擇照片來源")
        builder.setItems(option) {
            //_代表不使用的參數，而 which 代表選擇的索引值。
                _, witch ->
            when (witch) {
                0 -> checkPermissionOpenCamera()
                1 -> openGallery()
            }
        }
        builder.show()
    }

    //設定打開相機意圖的方法
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA)

    }

    //設定打開相簿的方法
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_GALLERY)

    }

    //檢查是否具有相機權限,沒有權限時並請求系統開啟相機的權限方法
    private fun checkPermissionOpenCamera() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.CAMERA), REQUEST_CARMERA_PERMISSION
            )
        } else {
            openCamera()
        }
    }


    private fun handleCameraResult(data: Intent?) {
        // data?.extras? 是一個bundle的對象,內包含intent相關數據,使用get("data")取的數據 轉Bitmap
        val imageBitmap = data?.extras?.get("data") as Bitmap
        imageByteArray = bitmapToBytes(imageBitmap)

        binding.ivBsShopProductModifyPhoto.apply {
            setImageBitmap(imageBitmap)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        viewModel.product.value?.apply {
            photo = imageByteArray
            name = ""
            price = 0
            description = ""
            storage = 0
            timeCreate = null
            timeUpdate = null
            isOnSale = false
        }

    }

    private fun handleGallery(data: Intent?) {
        val imageUri = data?.data
        if (imageUri != null) {
            binding.ivBsShopProductModifyPhoto.setImageURI(imageUri)
        }
        val contentResolver = context?.contentResolver
        val imageUrlBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
        imageByteArray = bitmapToBytes(imageUrlBitmap)
        viewModel.product.value?.apply {
            photo = imageByteArray
            name = ""
            price = 0
            description = ""
            storage = 0
            timeCreate = null
            timeUpdate = null
            isOnSale = false
        }
    }

    @Deprecated("Deprecated in Java")
    //當使用startActivityResult啟動一個活動結果,可以使用onActivityResult來承接活動返回的結果
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                //將REQUEST_CAMERA啟動獲得的data指派給handleCameraResult(data)承接,在顯示在imageview上
                REQUEST_CAMERA -> handleCameraResult(data)
                REQUEST_GALLERY -> handleGallery(data)
            }
        }
    }

}

