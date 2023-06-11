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
import com.example.cleaningapp.backstage.usermanage.model.Chat
import com.example.cleaningapp.backstage.usermanage.model.Chatroom
import com.example.cleaningapp.backstage.usermanage.model.Member
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserMainDetailViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainDetailBinding

class BsUserMainDetailFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserMainDetailBinding
    val viewModel: BsUserMainDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAlbBsUserMainDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().title = "用戶管理"

        /**
         * 從用戶管理Main的recyclerView接過來的bundle
         */
        arguments?.let { bundle ->
            bundle.getSerializable("member")?.let {
                binding.viewModel?.fetchMemberInfo(it as Member)
            }
            //新增從客服聊天室點選查詢,bundle customerId或cleanerId的會員資料解讀
//            arguments?.let { chatBundle ->
//                chatBundle.getSerializable("chat")?.let {
//                    if ((it as Chatroom).customerId == 0) {
//                        viewModel.fetchMemberInfo(it.cleanerId)
//                    } else {
//                        viewModel.fetchMemberInfo(it.customerId)
//                    }
//                }
//            }

            with(binding) {
                if (viewModel?.user?.value?.customerId != null){
                    tvBsUserMainDetailIdnum.visibility = View.GONE
                    tvBsUserMainDetailIdnumTi.visibility = View.GONE
                    tvBsUserMainDetailIdfrontTi.visibility = View.GONE
                    ivBsUserMainDetailIdfront.visibility = View.GONE
                    ivBsUserMainDetailIdback.visibility = View.GONE
                    tvBsUserMainDetailIdbackTi.visibility = View.GONE
                    ivBsUserMainDetailCriminalrecord.visibility = View.GONE
                    tvBsUserMainDetailCriminalrecordTi.visibility = View.GONE
                    llBsUserMainDetailPermission.visibility = View.GONE
                }
                if (viewModel?.user?.value?.backstageId != null){
                    ivBsUserMainDetailAvatar.visibility = View.GONE
                    llBsUserMainDetailPhone.visibility = View.GONE
                    tvBsUserMainDetailIntro.visibility = View.GONE
                    tvBsUserMainDetailIntroTi.visibility = View.GONE
                    tvBsUserMainDetailIdnum.visibility = View.GONE
                    tvBsUserMainDetailIdnumTi.visibility = View.GONE
                    tvBsUserMainDetailIdfrontTi.visibility = View.GONE
                    ivBsUserMainDetailIdfront.visibility = View.GONE
                    ivBsUserMainDetailIdback.visibility = View.GONE
                    tvBsUserMainDetailIdbackTi.visibility = View.GONE
                    ivBsUserMainDetailCriminalrecord.visibility = View.GONE
                    tvBsUserMainDetailCriminalrecordTi.visibility = View.GONE
                    llBsUserMainDetailPermission.visibility = View.GONE
                }

                btnBsUserMainDetailModify.setOnClickListener {
                    val bundle = Bundle()
                    val user = binding.viewModel?.user?.value
                    bundle.putSerializable("user", user)
                    Navigation.findNavController(view)
                        .navigate(
                            R.id.action_bsUserMainDetailFragment_to_bsUserMainModifyFragment,
                            bundle
                        )
                }
                btnBsUserMainDetailSuspend.setOnClickListener {
                    showAlertDialog()
                }
                ivBsUserMainDetailBack.setOnClickListener {
                    Navigation.findNavController(it).popBackStack()
                }
            }


        }
    }

    private fun showAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("確定要停權此帳號?")
        alertDialogBuilder.setMessage("將會通知訊息給使用者")

        alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
            dialog.dismiss()
            binding.viewModel?.user?.value?.suspend = true
            view?.let { viewModel.editMemberInfo(it) }
            Toast.makeText(requireContext(), "已將用戶停權", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("取消") { dialog, _ ->
            dialog.dismiss()

        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }
}