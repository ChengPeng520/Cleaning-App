package com.example.cleaningapp.backstage.usermanage.controller

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.Member
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserVerifyDetailViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsVerifyDetailBinding

class BsUserVerifyDetailFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsVerifyDetailBinding
    val viewModel: BsUserVerifyDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbBsVerifyDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().title = "審核管理"


        /**
         * 從用戶管理Main的recyclerView接過來的bundle
         */
        arguments?.let { bundle ->
            bundle.getSerializable("member")?.let {
                binding.viewModel?.fetchMemberInfo(it as Member)
            }

            with(binding) {
                btnBsUserVerifyDetailDecline.setOnClickListener {
                    declineShowAlertDialog()
                }
                ivBsUserVerifyDetailBack.setOnClickListener {
                    Navigation.findNavController(view).popBackStack()
                }
                btnBsUserVerifyDetailPass.setOnClickListener {
                    passShowAlertDialog()
                }
            }
        }
    }

    private fun passShowAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("確定要開通此帳號?")
        alertDialogBuilder.setMessage("將會通知訊息給使用者")

        alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
            dialog.dismiss()
            //TODO 將帳號的verify狀態update成1(目前有錯)
            binding.viewModel?.user?.value?.verify = true
            view?.let { viewModel.editMemberInfo(it) }
        }
        alertDialogBuilder.setNegativeButton("取消") { dialog, _ ->
            dialog.dismiss()

        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }

    private fun declineShowAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("確定拒絕開通此帳號?")
        alertDialogBuilder.setMessage("將會通知訊息給使用者")

        alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
            dialog.dismiss()
            Navigation.findNavController(binding.btnBsUserVerifyDetailDecline)
                .navigate(R.id.bsUserVerifyDeclineFragment)
        }
        alertDialogBuilder.setNegativeButton("取消") { dialog, _ ->
            dialog.dismiss()

        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }
}