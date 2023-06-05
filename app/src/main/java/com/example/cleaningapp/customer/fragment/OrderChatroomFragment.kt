package com.example.cleaningapp.customer.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.viewModel.OrderChatroomViewModel
import com.example.cleaningapp.databinding.FragmentVictorOrderChatroomBinding

class OrderChatroomFragment : Fragment() {
    private lateinit var binding: FragmentVictorOrderChatroomBinding
    private lateinit var order: Order
    val viewModel: OrderChatroomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVictorOrderChatroomBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            order = bundle.getSerializable("orderItem") as Order
            viewModel.order.value = order
        }

        with(binding){
            bntApplyComplaint.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.chatRoomFragment)
            }
        }
    }
}