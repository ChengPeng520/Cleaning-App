package com.example.cleaningapp.customer.csCreateOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Order
import com.example.cleaningapp.databinding.FragmentCsOrderConfirmedBinding

class CsOrderConfirmedFragment : Fragment() {
    private lateinit var binding:FragmentCsOrderConfirmedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsOrderConfirmedViewModel by viewModels()
        binding = FragmentCsOrderConfirmedBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            arguments?.let { bundle ->
                bundle.getSerializable("order")?.let {
                    viewModel?.order?.value = it as Order
                }
            }
            btCsOderConfirmedSubmit.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.action_csOrderConfirmedFragment_to_csOrderEstablishedFragment)
//                Toast.makeText(context,context?.getString(R.string.txt_csCreateOrder_orderEstablished),Toast.LENGTH_LONG).show()
            }
        }
    }
}