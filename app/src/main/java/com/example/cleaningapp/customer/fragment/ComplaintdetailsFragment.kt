package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.ComplaintdetailsViewModel
import com.example.cleaningapp.databinding.FragmentVictorComplaintdetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ComplaintdetailsFragment : Fragment() {
    private lateinit var binding: FragmentVictorComplaintdetailsBinding
    val viewModel: ComplaintdetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorComplaintdetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("orderId")?.let {
            viewModel.fetchOrdersInfo(it)
        }
        binding.bntApplyComplaintChat.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.chatRoomFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<BottomNavigationView>(R.id.bvn_customer).visibility =
            View.VISIBLE
    }
}