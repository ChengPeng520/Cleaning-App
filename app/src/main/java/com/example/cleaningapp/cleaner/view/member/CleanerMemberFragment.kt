package com.example.cleaningapp.cleaner.view.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentFatrueiMemberBinding

class CleanerMemberFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentFatrueiMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiMemberBinding.inflate(inflater, container, false)
        binding.clMemberInfo.setOnClickListener(this::onClick)
        binding.clMemberWindow.setOnClickListener(this::onClick)
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_member_info -> Navigation.findNavController(v)
                .navigate(R.id.action_memberFragment_to_memberInfoFragment)
            R.id.cl_member_window -> Navigation.findNavController(v)
                .navigate(R.id.action_memberFragment_to_contactWindowFragment)
        }
    }
}