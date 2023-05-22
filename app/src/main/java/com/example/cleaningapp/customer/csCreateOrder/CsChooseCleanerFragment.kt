package com.example.cleaningapp.customer.csCreateOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCleaners()
        binding.rvCsChooseCleaner.layoutManager = LinearLayoutManager(requireContext())
        viewModel?.cleanerList?.observe(viewLifecycleOwner) { cleaners ->
            if (binding.rvCsChooseCleaner.adapter == null) {
                binding.rvCsChooseCleaner.adapter = CsChooseCleanerAdapter(cleaners)
            } else {
                (binding.rvCsChooseCleaner.adapter as CsChooseCleanerAdapter).updateCleaners(cleaners)
            }
        }
    }
}