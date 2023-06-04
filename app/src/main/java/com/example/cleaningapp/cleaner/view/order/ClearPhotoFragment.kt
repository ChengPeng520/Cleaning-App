package com.example.cleaningapp.cleaner.view.order

import android.app.Activity
import android.content.ClipData.newIntent
import android.content.Intent
import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.order.ClearPhotoViewModel
import com.example.cleaningapp.databinding.FragmentVickyClearPhotoBinding

class ClearPhotoFragment : Fragment() {
private lateinit var binding: FragmentVickyClearPhotoBinding
    private val viewModel: ClearPhotoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVickyClearPhotoBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

           linearLayout4.setOnClickListener {
                if (viewModel?.capturedCount!! < 3) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    takePictureSmallLauncher.launch(intent)
                }
            }

            linearLayout5.setOnClickListener {
                if (viewModel?.capturedCount!! < 3) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    takePictureSmallLaunchers.launch(intent)
                }
            }
            btAddCheck.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_vicky_clear_photoFragment_to_orderStateFragment)
            }
        }
    }

    private var takePictureSmallLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras?.let { bundle ->
                    val picture = bundle["data"] as Bitmap?
                    picture?.let {
                        if (!binding.viewModel?.isPhotoExists(it)!! && binding.viewModel?.capturedCount!! < 3) {
                            binding.viewModel?.addCapturedPhoto(it)

                            when (binding.viewModel?.capturedCount) {
                                1 -> { binding.imageView28.setImageBitmap(it); binding.imageView33.setBackgroundResource(R.drawable.ic_add) }
                                2 -> { binding.imageView33.setImageBitmap(it); binding.imageView39.setBackgroundResource(R.drawable.ic_add) }
                                3 -> binding.imageView39.setImageBitmap(it)
                            }
                        }
                    }
                }
            }
        }
    private var takePictureSmallLaunchers =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras?.let { bundle ->
                    val picture = bundle["data"] as Bitmap?
                    picture?.let {
                        if (!binding.viewModel?.isPhotoExists(it)!! && binding.viewModel?.capturedCount!! < 3) {
                            binding.viewModel?.addCapturedPhoto(it)

                            when (binding.viewModel?.capturedCount) {
                                1 -> { binding.imageView40.setImageBitmap(it); binding.imageView41.setBackgroundResource(R.drawable.ic_add) }
                                2 -> { binding.imageView41.setImageBitmap(it); binding.imageView42.setBackgroundResource(R.drawable.ic_add) }
                                3 -> binding.imageView42.setImageBitmap(it)
                            }
                        }
                    }
                }
            }
        }
    }
