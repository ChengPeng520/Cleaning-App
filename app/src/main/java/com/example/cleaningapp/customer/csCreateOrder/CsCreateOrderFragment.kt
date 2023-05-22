package com.example.cleaningapp.customer.csCreateOrder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCsCreateOrderBinding
import java.util.*

class CsCreateOrderFragment : Fragment() {
    private lateinit var binding: FragmentCsCreateOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsCreateOrderViewModel by viewModels()
        binding = FragmentCsCreateOrderBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            tvCsCreateOrderChooseCoupon.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.action_csCreateOrderFragment_to_csCouponPickerFragment)
            }
            btnCsCreateOrderNext.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.action_csCreateOrderFragment_to_csOrderConfirmedFragment)
            }

            llDatePicker.setOnClickListener {
                // calendar要宣告為此區域的變數，這樣每次點擊按鈕跳出的預選日期方能維持當下日期；
                // 如果宣告在此區域外，下方程式calendar加一個月，會影響日期挑選器預選日期
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

            llCsCreateOrderTimeBegin.setOnClickListener {
                val calendar = Calendar.getInstance()
                TimePickerDialog(
                    requireContext(),
                    { _, hour, minute ->
                        viewModel?.textTimeBegin?.value = "${pad(hour)}:${pad(minute)}"
                    },
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    false
                ).show()
            }

            llCsCreateOrderTimeEnd.setOnClickListener {
                val calendar = Calendar.getInstance()
                TimePickerDialog(
                    requireContext(),
                    { _, hour, minute ->
                        viewModel?.textTimeEnd?.value = "${pad(hour)}:${pad(minute)}"
                    },
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    false
                ).show()
            }




//            //Spinner 選擇縣市&地區
//            val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    viewModel?.text?.value = parent?.getItemAtPosition(position).toString()
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    viewModel?.text?.value = getString(R.string.textNothingSelected)
//                }
//            }
//            /* 註冊OnItemSelectedListener監聽器之前先呼叫setSelection()，
//                不僅可以設定一開始預選項目，還可避免Spinner一開始就執行
//                OnItemSelectedListener.onItemClick()的問題 */
//            spFood.setSelection(0, true)
//            spPlace.setSelection(0, true)
//            spFood.onItemSelectedListener = onItemSelectedListener
//            spPlace.onItemSelectedListener = onItemSelectedListener
        }
    }

    private fun pad(number: Int): String {
        return if (number >= 10) {
            number.toString()
        } else {
            "0$number"
        }
    }


}