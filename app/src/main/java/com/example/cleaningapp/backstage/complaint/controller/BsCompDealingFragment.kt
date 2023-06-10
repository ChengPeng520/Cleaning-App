package com.example.cleaningapp.backstage.complaint.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.complaint.model.BSCompOrderItem
import com.example.cleaningapp.backstage.complaint.model.Complaint
import com.example.cleaningapp.backstage.complaint.viewModel.BsCompDealingViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsCompDealingBinding

class BsCompDealingFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsCompDealingBinding
    val viewModel: BsCompDealingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbBsCompDealingBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun showDialogAgree() {
        val alertDialogBuilder = AlertDialog.Builder(binding.root.context)
        alertDialogBuilder.setTitle("確定同意客訴申請？")
        alertDialogBuilder.setMessage("將發送訊息給使用者")
        alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
            dialog.dismiss()
            // 同意後修改，跳轉bsCompDetailFragment，並將資料bundle過去
            val bundle = Bundle()
            viewModel.uiState.value?.let { bundle.putInt("orderIdDealing", it.orderId) }
            // 修改
            arguments?.getInt("orderIdMain")?.let { viewModel.editComplaint(it) }
            view?.let { Navigation.findNavController(it).navigate(R.id.bsCompDetailFragment, bundle) }
        }
        alertDialogBuilder.setNegativeButton("取消") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.show()
    }

    private fun showDialogDecline() {
        val alertDialogBuilder = AlertDialog.Builder(binding.root.context)
        alertDialogBuilder.setTitle("確定拒絕客訴申請？")
        alertDialogBuilder.setMessage("將發送訊息給使用者")
        alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
            dialog.dismiss()
            view?.let { Navigation.findNavController(it).popBackStack() }
        }
        alertDialogBuilder.setNegativeButton("取消") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            bundle.getInt("orderIdMain").let { viewModel.loadComplaint(it) }
        }

        with(binding) {
            btnBsCompDealingAgree.setOnClickListener {
                showDialogAgree()
            }

            btnBsCompDealingDecline.setOnClickListener {
                showDialogDecline()
            }

            ivBsCompDealingBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
        }
    }
}