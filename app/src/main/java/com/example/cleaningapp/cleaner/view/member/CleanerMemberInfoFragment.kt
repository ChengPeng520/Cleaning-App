package com.example.cleaningapp.cleaner.view.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
}