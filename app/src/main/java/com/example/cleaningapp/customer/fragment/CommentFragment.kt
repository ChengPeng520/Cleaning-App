// CommentFragment.kt

package com.example.cleaningapp.customer.fragment

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
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.CommentViewModel
import com.example.cleaningapp.databinding.FragmentVictorCommentBinding
import java.io.File

class CommentFragment : Fragment() {
    private lateinit var binding: FragmentVictorCommentBinding
    private val viewModel: CommentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorCommentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCommentOk.setOnClickListener {
            Navigation.findNavController(binding.btnCommentOk)
                .navigate(R.id.action_commentFragment_to_commentDoneFragment)
        }

        binding.imageView43.setOnClickListener {
            if (viewModel.capturedCount < 3) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureSmallLauncher.launch(intent)
            }
        }
    }

    private var takePictureSmallLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras?.let { bundle ->
                    val picture = bundle["data"] as Bitmap?
                    picture?.let {
                        if (!viewModel.isPhotoExists(it) && viewModel.capturedCount < 3) {
                            viewModel.addCapturedPhoto(it)

                            when (viewModel.capturedCount) {
                                1 -> binding.imageView44.setImageBitmap(it)
                                2 -> binding.imageView45.setImageBitmap(it)
                                3 -> binding.imageView43.setImageBitmap(it)
                            }
                        }
                    }
                }
            }
        }
}
