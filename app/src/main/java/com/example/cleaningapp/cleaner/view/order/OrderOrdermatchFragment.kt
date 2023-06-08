package com.example.cleaningapp.cleaner.view.order


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.Work
import com.example.cleaningapp.cleaner.viewmodel.order.OrderOrdermatchViewModel
import com.example.cleaningapp.databinding.FragmentVickyOrderOrdermatchBinding

class OrderOrdermatchFragment : Fragment() {
    private lateinit var binding: FragmentVickyOrderOrdermatchBinding
    private val viewModel: OrderOrdermatchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVickyOrderOrdermatchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getInt("orderId")?.let {
                viewModel.fetchOrdermatch(it)
            }
        }
        with(binding) {
            tvOrderCancel.setOnClickListener {
//                viewModel.DeleteOrder()
                Navigation.findNavController(view)
                    .navigate(R.id.action_vicky_order_ordermatchFragment_to_vicky_order_conductFragment)
            }
        }
    }
}


