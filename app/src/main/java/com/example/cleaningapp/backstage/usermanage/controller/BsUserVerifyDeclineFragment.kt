package com.example.cleaningapp.backstage.usermanage.controller

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserVerifyDeclineViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserVerifyDeclineBinding

class BsUserVerifyDeclineFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserVerifyDeclineBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel: BsUserVerifyDeclineViewModel by viewModels()
        binding = FragmentAlbBsUserVerifyDeclineBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnBsUserVerifyDeclineSubmit.setOnClickListener {
                if (edtTxtBsUserVerifyDecline.text.toString().isNotEmpty()) {
                    showAlertDialog()
                } else {
                    showToast("請填寫內容")
                }
            }
            ivBsUserVerifyDeclineBack.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }

        }
    }

    private fun showAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("確定送出未通過訊息?")
        alertDialogBuilder.setMessage("將發送訊息給使用者")

        alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
            dialog.dismiss()
            Navigation.findNavController(binding.btnBsUserVerifyDeclineSubmit)
                .navigate(R.id.bsUserVerifyFragment)
        }
        alertDialogBuilder.setNegativeButton("取消") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}


