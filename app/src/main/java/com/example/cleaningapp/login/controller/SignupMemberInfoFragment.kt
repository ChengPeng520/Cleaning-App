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
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentSignupMemberInfoBinding
import com.example.cleaningapp.login.viewModel.SignupMemberInfoViewModel
import com.example.cleaningapp.share.ImageUtils

class SignupMemberInfoFragment : Fragment() {
    private lateinit var binding: FragmentSignupMemberInfoBinding
    private val viewModel: SignupMemberInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupMemberInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<Toolbar>(R.id.login_toolbar).visibility = View.VISIBLE
        requireActivity().findViewById<TextView>(R.id.login_toolbar_title).text = "會員資料填寫"
        with(binding) {
            ivMemInfoBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            tvMemInfoDone.setOnClickListener {
                if (!inputCheck()) {
                    return@setOnClickListener
                }

                arguments?.let { bundle ->
                    viewModel?.email = bundle.getString("emailAccount")
                    viewModel?.password = bundle.getString("password")
                    println(bundle.getString("emailAccount"))
                }

                viewModel?.customerRegister()?.let {
                    if (it) {
                        Toast.makeText(context, "註冊成功", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView3)
                            .navigate(R.id.action_signupMemberInfoFragment_to_loginFragment)
                    } else {
                        Toast.makeText(context, "註冊失敗", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            ivMemInfoAvatar.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncherAvatar.launch(intent)
            }
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
            return check
        }
    }

    private var pickPictureLauncherAvatar =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    viewModel.avatar.value = ImageUtils.uriToBitmap(requireContext(), uri)
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}