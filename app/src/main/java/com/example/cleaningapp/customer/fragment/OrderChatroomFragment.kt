package com.example.cleaningapp.customer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.OrderChatroomViewModel
import com.example.cleaningapp.databinding.FragmentVictorOrderChatroomBinding

class OrderChatroomFragment : Fragment() {
    private lateinit var binding: FragmentVictorOrderChatroomBinding
    val viewModel: OrderChatroomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorOrderChatroomBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text = getString(R.string.csTitle_orderHistory)
        arguments?.getInt("orderId")?.let {
            viewModel.fetchOrdersInfo(it)
            viewModel.orderId.value = it
        }


        with(binding) {
            bntApplyComplaint.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.chatRoomFragment)
            }
        }
    }
}