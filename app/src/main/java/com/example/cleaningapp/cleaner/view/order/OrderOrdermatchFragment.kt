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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVickyOrderOrdermatchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            arguments?.let { bundle ->
                bundle.getSerializable("order")?.let {
                    binding.viewModel?.order?.value = it as Work
                }
        }
        with(binding){
            val onClickListener = DialogInterface.OnClickListener { dialog, which ->
                val text = when (which) {
                    AlertDialog.BUTTON_POSITIVE -> getString(R.string.txtYes)
                    AlertDialog.BUTTON_NEGATIVE -> getString(R.string.txtNo)

                    else -> ""
                }
                viewModel?.text?.value = text
                dialog.cancel()
            }
            tvOrderCancel.setOnClickListener{
                android.app.AlertDialog.Builder(view.context)
                    // 設定標題文字
                    .setTitle(R.string.txtTitle)
                    // 設定圖示
                    .setIcon(R.drawable.alert)
                    // 設定訊息文字
                    .setMessage(R.string.txtMessage)
                    // 設定positive, negative, neutral按鈕上面的文字與點擊事件監聽器
                    .setPositiveButton(R.string.txtYes, onClickListener)
                    .setNegativeButton(R.string.txtNo, onClickListener)

                    .show()

                Navigation.findNavController(view)
                    .navigate(R.id.action_vicky_order_ordermatchFragment_to_vicky_order_conductFragment)

            }
        }
    }
}


