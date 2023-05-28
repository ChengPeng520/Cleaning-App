package com.example.cleaningapp.cleaner.view.member

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setObserve()
        setOnclick()
    }

    private fun setObserve() {
        with(binding) {
            viewModel?.uiState?.observe(viewLifecycleOwner) {
                edtTxtMemberInfoName.textAlignment =
                    if (it.name.isEmpty()) View.TEXT_ALIGNMENT_TEXT_END
                    else View.TEXT_ALIGNMENT_TEXT_START

                edtTxtMemberInfoIdentity.textAlignment =
                    if (it.identifyNumber.isEmpty()) View.TEXT_ALIGNMENT_TEXT_END
                    else View.TEXT_ALIGNMENT_TEXT_START

                edtTxtMemberInfoPhone.textAlignment =
                    if (it.phone.isEmpty()) View.TEXT_ALIGNMENT_TEXT_END
                    else View.TEXT_ALIGNMENT_TEXT_START
            }
        }
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
            btMemberInfoEditPic.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher.launch(intent)
            }
            ivMemberInfoIdCardFront.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher2.launch(intent)
            }
            ivMemberInfoIdCardBack.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher3.launch(intent)
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

    private var pickPictureLauncher2 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    val currentState = viewModel.uiState.value ?: CleanerMemberInfoUiState()
                    currentState.idCardFront = ImageUtils.uriToBitmap(requireContext(), uri)
                    viewModel.uiState.value = currentState
                }
            }
        }

    private var pickPictureLauncher3 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    val currentState = viewModel.uiState.value ?: CleanerMemberInfoUiState()
                    currentState.idCardBack = ImageUtils.uriToBitmap(requireContext(), uri)
                    viewModel.uiState.value = currentState
                }
            }
        }
}