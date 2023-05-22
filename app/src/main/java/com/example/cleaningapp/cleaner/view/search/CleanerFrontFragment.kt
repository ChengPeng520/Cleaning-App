package com.example.cleaningapp.cleaner.view.search

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.CleanerAdapter
import com.example.cleaningapp.cleaner.viewmodel.search.CleanerFrontViewModel
import com.example.cleaningapp.databinding.FragmentVickyCleanerFrontBinding
import java.util.*

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
            val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel?.text?.value = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    viewModel?.text?.value = getString(R.string.textNothingSelected)
                }
            }
            spnCounty.setSelection(0, true)
            spnLocaltion.setSelection(0, true)
            spnCounty.onItemSelectedListener = onItemSelectedListener
            spnLocaltion.onItemSelectedListener = onItemSelectedListener

            spnDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val datePickerDialog =
                    DatePickerDialog(
                        requireContext(),
                        { _, year, month, day ->
                            // 一月的值是0而非1，所以「month + 1」後才顯示
                            viewModel?.textDate?.value = "$year-${pad(month + 1)}-${pad(day)}"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                // 取得DatePicker物件方可設定可選取的日期區間
                val datePicker = datePickerDialog.datePicker
                // 設定可選取的開始日為今日
                datePicker.minDate = calendar.timeInMillis
                // 設定可選取的結束日為一個月後
                calendar.add(Calendar.MONTH, 1)
                datePicker.maxDate = calendar.timeInMillis
                // 最後要呼叫show()方能顯示
                datePickerDialog.show()
            }
            spnTime.setOnClickListener {
                val calendar = Calendar.getInstance()
                TimePickerDialog(
                    requireContext(),
                    { _, hour, minute ->
                        viewModel?.textTime?.value = "${pad(hour)}:${pad(minute)}"
                    },
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    false
                ).show()
                // result?
            }
            spinner3.setOnClickListener {
                val calendar = Calendar.getInstance()
                TimePickerDialog(
                    requireContext(),
                    { _, hour, minute ->
                        viewModel?.textTime2?.value = "${pad(hour)}:${pad(minute)}"
                    },
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    false
                ).show()
                // result?
            }
        }
    }
}

private fun pad(number: Int): String {
    return if (number >= 10) {
        number.toString()
    } else {
        "0$number"
    }
}



