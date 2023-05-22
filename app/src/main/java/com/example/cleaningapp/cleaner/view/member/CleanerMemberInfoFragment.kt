package com.example.cleaningapp.cleaner.view.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cleaningapp.cleaner.viewmodel.member.CleanerMemberInfoViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiMemberInfoBinding

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
        initView()
        return binding.root
    }

    private fun initView() {
        with(binding) {
            viewModel?.name?.observe(viewLifecycleOwner) {
                edtTxtMemberInfoName.textAlignment =
                    if (it == null || it.isEmpty()) View.TEXT_ALIGNMENT_TEXT_END
                    else View.TEXT_ALIGNMENT_TEXT_START
            }

            viewModel?.identityNumber?.observe(viewLifecycleOwner) {
                edtTxtMemberInfoIdentity.textAlignment =
                    if (it == null || it.isEmpty()) View.TEXT_ALIGNMENT_TEXT_END
                    else View.TEXT_ALIGNMENT_TEXT_START
            }

            viewModel?.phone?.observe(viewLifecycleOwner) {
                edtTxtMemberInfoPhone.textAlignment =
                    if (it == null || it.isEmpty()) View.TEXT_ALIGNMENT_TEXT_END
                    else View.TEXT_ALIGNMENT_TEXT_START
            }
        }
    }
}