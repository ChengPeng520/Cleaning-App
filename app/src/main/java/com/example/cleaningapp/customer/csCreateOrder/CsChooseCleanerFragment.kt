package com.example.cleaningapp.customer.csCreateOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.adapter.CsChooseCleanerAdapter
import com.example.cleaningapp.databinding.FragmentCsChooseCleanerBinding

class CsChooseCleanerFragment : Fragment() {
    private lateinit var binding: FragmentCsChooseCleanerBinding
    val viewModel: CsChooseCleanerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCsChooseCleanerBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text =
            getString(R.string.csTitle_chooseCleaner)
        arguments?.let { bundle ->
            bundle.getInt("orderId").let {
                viewModel.orderId = it
                viewModel.fetchCleanerApplied(it)
            }
        }
        binding.btnCsChooseCleanerCancelOrder.setOnClickListener {
            if (viewModel.cancelOrder()) {
                Toast.makeText(
                    it.context,
                    requireActivity().getString(R.string.toast_CsChooseCleanerCancelOrder),
                    Toast.LENGTH_SHORT
                ).show()
                Navigation.findNavController(it)
                    .navigate(R.id.action_csChooseCleanerFragment2_to_historicalorderFragment)
            } else {
                Toast.makeText(
                    it.context,
                    "訂單取消失敗",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.rvCsChooseCleaner.layoutManager = LinearLayoutManager(requireContext())
        viewModel.cleanerList.observe(viewLifecycleOwner) { cleaners ->
            if (binding.rvCsChooseCleaner.adapter == null) {
                binding.rvCsChooseCleaner.adapter =
                    CsChooseCleanerAdapter(cleaners, viewModel.orderId)
            } else {
                (binding.rvCsChooseCleaner.adapter as CsChooseCleanerAdapter).updateCleaners(
                    cleaners
                )
            }
        }
    }
}