package com.example.cleaningapp.cleaner.view.member

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.CleanerMemberInfoUiState
import com.example.cleaningapp.cleaner.viewmodel.member.CleanerMemberInfoViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiMemberInfoBinding
import com.example.cleaningapp.share.ImageUtils

class CleanerMemberInfoFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiMemberInfoBinding
    private val viewModel: CleanerMemberInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiMemberInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        requireActivity().findViewById<TextView>(R.id.cleaner_toolbar_title).text = "會員資料"
    }

    private fun initView() {
        setOnclick()
    }

    private fun setOnclick() {
        with(binding) {
            cvMemberInfoImg.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher.launch(intent)
            }
        }
    }

    private var pickPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    val currentState = viewModel.uiState.value ?: CleanerMemberInfoUiState()
                    currentState.photo = ImageUtils.uriToBitmap(requireContext(), uri)
                    viewModel.uiState.value = currentState
                }
            }
        }
}