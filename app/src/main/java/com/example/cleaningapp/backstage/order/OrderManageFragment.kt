package com.example.cleaningapp.backstage.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCiyiOrderManageBinding

class OrderManageFragment : Fragment() {
    private lateinit var binding: FragmentCiyiOrderManageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        requireActivity().setTitle(R.string.menu_backstage_orderManage)
        binding = FragmentCiyiOrderManageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.menu_backstage_orderManage)

    }


}