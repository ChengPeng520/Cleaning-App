package com.example.cleaningapp.cleaner.view.order

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.cleaner.viewmodel.order.ClearPhotoViewModel
import com.example.cleaningapp.databinding.FragmentVickyClearPhotoBinding
import com.example.cleaningapp.share.CleanerPreferencesUtils

class ClearPhotoFragment : Fragment() {
    private lateinit var binding: FragmentVickyClearPhotoBinding
    private val viewModel: ClearPhotoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVickyClearPhotoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            arguments?.getInt("orderId")?.let {
                viewModel?.cleanerBeforePhoto(it)
            }

            linearLayout5.setOnClickListener {
                if (viewModel?.photo?.value?.photo3 == null) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    takePictureSmallLauncher.launch(intent)
                }
            }
            btAddCheck.setOnClickListener {
                CleanerPreferencesUtils.saveCleaningPhotoFromPreferences(
                    requireActivity(),
                    viewModel?.photos!!
                )
                Navigation.findNavController(view)
                    .popBackStack()
            }
        }
    }

    private var takePictureSmallLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras?.let { bundle ->
                    val picture = bundle["data"] as Bitmap?
                    picture?.let {
                        viewModel.addCapturedPhoto(it)
                    }
                }
            }
        }
}

//  - - - - -

//           linearLayout4.setOnClickListener {
//           }                if (viewModel?.capturedCount!! < 3) {
//                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                    takePictureSmallLauncher.launch(intent)
//                }

//
//    private var takePictureSmallLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                result.data?.extras?.let { bundle ->
//                    val picture = bundle["data"] as Bitmap?
//                    picture?.let {
//                        if (!binding.viewModel?.isPhotoExists(it)!! && binding.viewModel?.capturedCount!! < 3) {
//                            binding.viewModel?.addCapturedPhoto(it)
//
//                            when (binding.viewModel?.capturedCount) {
//                                1 -> { binding.imageView28.setImageBitmap(it); binding.imageView33.setBackgroundResource(R.drawable.ic_add) }
//                                2 -> { binding.imageView33.setImageBitmap(it); binding.imageView39.setBackgroundResource(R.drawable.ic_add) }
//                                3 -> binding.imageView39.setImageBitmap(it)
//                            }
//                        }
//                    }
//                }
//            }
//        }
