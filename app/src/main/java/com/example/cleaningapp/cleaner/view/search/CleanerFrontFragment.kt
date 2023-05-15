package com.example.cleaningapp.cleaner.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.CleanerAdapter
import com.example.cleaningapp.cleaner.viewmodel.search.CleanerFrontViewModel
import com.example.cleaningapp.databinding.FragmentVickyCleanerFrontBinding

class CleanerFrontFragment : Fragment() {
    private lateinit var binding: FragmentVickyCleanerFrontBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CleanerFrontViewModel by viewModels()
        binding = FragmentVickyCleanerFrontBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerviewSearchlist.layoutManager = LinearLayoutManager(requireContext())
            //layoutManager管理每一個layout裡面每個item呈現方式
            viewModel?.cleaner?.observe(viewLifecycleOwner) { cleaners ->
                // cleaners 類別是為viewModel中的<List<Job>>
                // adapter 為null要建立新的adapter；之後只要呼叫updateCleaner(cleaners)即可
                // observe 監測、觀察者(因為跟livedata綁定須監測)
                if (recyclerviewSearchlist.adapter == null) {
                    recyclerviewSearchlist.adapter = CleanerAdapter(cleaners)

                } else {
                    (recyclerviewSearchlist.adapter as CleanerAdapter).updateCleaners(cleaners)
                }
            }
            val onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ){
                    viewModel?.text?.value = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    viewModel?.text?.value = getString(R.string.textNothingSelected)
                }
            }
            spnDate.setSelection(0, true)
            spnTime.setSelection(0, true)
            spinner3.setSelection(0, true)
            spnCounty.setSelection(0, true)
            spnLocaltion.setSelection(0, true)
            spnDate.onItemSelectedListener = onItemSelectedListener
            spnTime.onItemSelectedListener = onItemSelectedListener
            spinner3.onItemSelectedListener = onItemSelectedListener
            spnCounty.onItemSelectedListener = onItemSelectedListener
            spnLocaltion.onItemSelectedListener = onItemSelectedListener
        }
    }
}


