package com.example.cleaningapp.cleaner.view.member

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.cleaner.viewmodel.member.CleanerMemberInfoViewModel
import com.example.cleaningapp.data.datasource.MemberLocalDataSource
import com.example.cleaningapp.data.datasource.api.impl.MemberApiImpl
import com.example.cleaningapp.data.repository.impl.MemberRepositoryImpl
import com.example.cleaningapp.databinding.FragmentFatrueiMemberInfoBinding

class CleanerMemberInfoFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiMemberInfoBinding
    private val viewModel: CleanerMemberInfoViewModel by viewModels {
        CleanerMemberInfoViewModel.provideFactory(
            requireActivity().application, MemberRepositoryImpl(
                MemberLocalDataSource(MemberApiImpl())
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiMemberInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        }
    }

    private fun initView() {
        with(binding) {
            viewModel?.uiState?.observe(viewLifecycleOwner) {
                edtTxtMemberInfoName.textAlignment =
                    if (it.name.isEmpty()) View.TEXT_ALIGNMENT_TEXT_END
                    else View.TEXT_ALIGNMENT_TEXT_START

                edtTxtMemberInfoIdentity.textAlignment =
                    if (it.identityNumber.isEmpty()) View.TEXT_ALIGNMENT_TEXT_END
                    else View.TEXT_ALIGNMENT_TEXT_START

                edtTxtMemberInfoPhone.textAlignment =
                    if (it.phone.isEmpty()) View.TEXT_ALIGNMENT_TEXT_END
                    else View.TEXT_ALIGNMENT_TEXT_START
            }
        }
    }

    private var pickPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> binding.ivMemberInfoImg.setImageURI(uri) }
            }
        }
}