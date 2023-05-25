package com.example.cleaningapp.backstage.shop.controller


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.backstage.shop.viewModel.BsShopProductAddViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsShopProductAddBinding


class BsShopProductAddFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsShopProductAddBinding
    private lateinit var showPictureDialog: ShowPictureDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: BsShopProductAddViewModel by viewModels()
        binding = FragmentAlbBsShopProductAddBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        showPictureDialog = ShowPictureDialog(requireActivity(), binding.ivBsShopProductAddPhoto)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            btnProductAdd.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
            btnCameraProductAdd.setOnClickListener {
                showPictureDialog.showPictureDialog()

            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val image = showPictureDialog.handleCameraResult(resultCode,requestCode,data)
        binding.ivBsShopProductAddPhoto.setImageBitmap(image)
        binding.ivBsShopProductAddPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
    }

}




