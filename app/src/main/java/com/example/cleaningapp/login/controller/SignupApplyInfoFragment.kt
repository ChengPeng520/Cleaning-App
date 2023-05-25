package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaSignupApplyInfoBinding
import com.example.cleaningapp.login.viewModel.SignupApplyInfoViewModel

class SignupApplyInfoFragment : Fragment() {
    private lateinit var binding: FragmentRonaSignupApplyInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "申請資料"
        val viewModel: SignupApplyInfoViewModel by viewModels()
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
                Navigation.findNavController(it)
                    .navigate(R.id.action_signupApplyInfoFragment_to_signupCheckApplyFragment)
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

    private var pickPictureLauncherId1 =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> binding.ivSuInfoId1.setImageURI(uri) }
            }
        }
    private var pickPictureLauncherId2 =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> binding.ivSuInfoId2.setImageURI(uri) }
            }
        }
    private var pickPictureLauncherGP =
        registerForActivityResult(StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK){
                result.data?.data?.let { uri -> binding.ivSuInfoGoodPerson.setImageURI(uri) }
            }
        }


    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val fullName = viewModel?.fullName?.value?.trim()
            val phone = viewModel?.phone?.value?.trim()

            if (fullName == null || fullName.isEmpty() || phone == null || phone.isEmpty()) {
                Toast.makeText(context, "姓名或手機不可空白", Toast.LENGTH_SHORT).show()
                check = false
            }

//            val id1 = viewModel?.id1?.value
//            val id2 = viewModel?.id2?.value
//            val goodPerson = viewModel?.goodPerson?.value
//
//            if ( id1 == null || id2 == null || goodPerson == null){
//                Toast.makeText(context, "請上傳照片", Toast.LENGTH_SHORT).show()
//                check = false
//            }
            return check
        }
    }
}