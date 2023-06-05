package com.example.cleaningapp.customer.csUserPage

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
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Customer
import com.example.cleaningapp.customer.model.Order
import com.example.cleaningapp.databinding.FragmentCsEditProfileBinding

class CsEditProfileFragment : Fragment() {
    private lateinit var binding: FragmentCsEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsEditProfileViewModel by viewModels()
        binding = FragmentCsEditProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.setTitle(R.string.csTitle_editProfile)

        with(binding) {
            arguments?.let { bundle ->
                bundle.getSerializable("profile")?.let {
                    viewModel?.profile?.value = it as Customer
                }
            }
            btnCsEditProfileRestore.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
            cvCsEditProfileImg.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher.launch(intent)
            }
            btCsEditProfileEditPic.setOnClickListener {
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
                result.data?.data?.let { uri -> binding.ivCsEditProfilePic.setImageURI(uri) }
            }
        }
}