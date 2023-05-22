package com.example.cleaningapp.cleaner.view.order

import android.app.Activity
import android.content.ClipData.newIntent
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
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
        binding = FragmentVickyClearPhotoBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_vicky_clear_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            imageView28.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                imageView28Launcher.launch(intent)
            }
        }
    }

    private var imageView28Launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> binding.imageView28.setImageURI(uri) }
            }
        }
    }
