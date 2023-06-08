package com.example.cleaningapp.cleaner.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.order.OrderAcceptViewModel
import com.example.cleaningapp.databinding.FragmentVickyOrderAcceptBinding

class OrderAcceptFragment : Fragment() {
    private lateinit var binding: FragmentVickyOrderAcceptBinding
    private lateinit var viewModel: OrderAcceptViewModel

    companion object {
        fun newInstance() = OrderAcceptFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vicky_order_accept, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderAcceptViewModel::class.java)
        // TODO: Use the ViewModel
        with(binding) {

            tvOrderAcceptBackhome.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_order_acceptFragment_to_cleanerFrontFragment)
            }
        }
    }

}