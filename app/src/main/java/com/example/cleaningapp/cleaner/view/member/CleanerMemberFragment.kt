package com.example.cleaningapp.cleaner.view.member

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cleaningapp.LoginActivity
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.member.CleanerMemberViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiMemberBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CleanerMemberFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentFatrueiMemberBinding
    private val viewModel: CleanerMemberViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiMemberBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        binding.clMemberInfo.setOnClickListener(this::onClick)
        binding.clMemberWindow.setOnClickListener(this::onClick)
        binding.btnMemberSignout.setOnClickListener(this::onClick)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_member_info -> findNavController().navigate(R.id.action_memberFragment_to_memberInfoFragment)
            R.id.cl_member_window -> findNavController().navigate(R.id.action_memberFragment_to_contactWindowFragment)
            R.id.btn_member_signout -> {
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<BottomNavigationView>(R.id.bvn_cleaner).visibility =
            View.VISIBLE
        viewModel.fetchPhoto()
    }
}