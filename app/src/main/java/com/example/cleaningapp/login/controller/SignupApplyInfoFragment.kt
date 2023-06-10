package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaSignupApplyInfoBinding
import com.example.cleaningapp.login.viewModel.SignupApplyInfoViewModel
import com.example.cleaningapp.share.ImageUtils.uriToBitmap

class SignupApplyInfoFragment : Fragment() {
    private lateinit var binding: FragmentRonaSignupApplyInfoBinding
    val viewModel: SignupApplyInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "申請資料"
        binding = FragmentRonaSignupApplyInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            ivSuInfoBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            tvSuInfoDone.setOnClickListener {
                if (!inputCheck()) {
                    return@setOnClickListener
                }
                arguments?.let { bundle ->
                    viewModel?.email = bundle.getString("emailAccount")
                    viewModel?.password = bundle.getString("password")
                    println(bundle.getString("emailAccount"))
                }

                viewModel?.cleanerRegister()?.let {
                    if (it) {
                        Toast.makeText(context, "註冊成功", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView3)
                            .navigate(R.id.action_signupApplyInfoFragment_to_signupCheckApplyFragment)
                    } else {
                        Toast.makeText(context, "註冊失敗", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            ivSuInfoAvatar.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncherAvatar.launch(intent)
            }

            ivSuInfoId1.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncherId1.launch(intent)
            }

            ivSuInfoId2.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncherId2.launch(intent)
            }

            ivSuInfoGoodPerson.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncherGP.launch(intent)
            }
        }
    }

    private var pickPictureLauncherAvatar =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    viewModel.avatar.value = uriToBitmap(requireContext(), uri)
                }
            }
        }

    private var pickPictureLauncherId1 =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    viewModel.id1.value = uriToBitmap(requireContext(), uri)
                }
            }
        }
    private var pickPictureLauncherId2 =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    viewModel.id2.value = uriToBitmap(requireContext(), uri)
                }
            }
        }
    private var pickPictureLauncherGP =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    viewModel.crc.value = uriToBitmap(requireContext(), uri)
                }
            }
        }

    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val fullName = viewModel?.fullName?.value?.trim()
            val phone = viewModel?.phone?.value?.trim()
            val avatar = viewModel?.avatar?.value
            val identifyNumber = viewModel?.identifyNumber?.value?.trim()
            val id1 = viewModel?.id1?.value
            val id2 = viewModel?.id2?.value
            val goodPerson = viewModel?.crc?.value

            if (fullName == null || fullName.isEmpty() || phone == null || phone.isEmpty()) {
                Toast.makeText(context, "姓名或手機不可空白", Toast.LENGTH_SHORT).show()
                check = false
            }

            if (identifyNumber == null) {
                Toast.makeText(context, "身份證字號不可空白", Toast.LENGTH_SHORT).show()
                check = false
            }

            if (avatar == null) {
                Toast.makeText(context, "請上傳本人照片", Toast.LENGTH_SHORT).show()
                check = false
            }

            if (id1 == null || id2 == null) {
                Toast.makeText(context, "請上傳身分證照片", Toast.LENGTH_SHORT).show()
                check = false
            }

            if (goodPerson == null) {
                Toast.makeText(context, "請上傳良民證照片", Toast.LENGTH_SHORT).show()
                check = false
            }

            return check
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}