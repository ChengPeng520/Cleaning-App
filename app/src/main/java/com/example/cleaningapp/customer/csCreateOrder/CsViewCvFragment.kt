package com.example.cleaningapp.customer.csCreateOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.CustomerViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.adapter.CsCommentAdapter
import com.example.cleaningapp.databinding.FragmentCsViewCvBinding
import com.example.cleaningapp.share.TapPay

class CsViewCvFragment : Fragment() {
    private lateinit var binding: FragmentCsViewCvBinding
    private val viewModel: CsViewCvViewModel by viewModels()
    private lateinit var activityViewModel: CustomerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCsViewCvBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        activityViewModel = ViewModelProvider(requireActivity())[CustomerViewModel::class.java]
        activityViewModel.resultLiveData.observe(viewLifecycleOwner) {
            if (it) {
                if (viewModel.checkout()) {
//                    findNavController().navigate()
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text = getString(R.string.csTitle_viewCv)
        arguments?.let { bundle ->
            bundle.getInt("cleanerId").let {
                viewModel.fetchCleanerInfo(it)
                viewModel.fetchComments(it)
                val orderEstablished = viewModel.orderEstablished.value
                orderEstablished?.cleanerId = it
                viewModel.orderEstablished.value = orderEstablished
            }
            bundle.getInt("orderId").let {
                viewModel.fetchOrdersInfo(it)
                val orderEstablished = viewModel.orderEstablished.value
                orderEstablished?.orderId = it
                viewModel.orderEstablished.value = orderEstablished
            }
        }
        binding.rvCsViewComment.layoutManager = LinearLayoutManager(requireContext())
        binding.viewModel?.comments?.observe(viewLifecycleOwner) { comments ->
            if (binding.rvCsViewComment.adapter == null) {
                binding.rvCsViewComment.adapter = CsCommentAdapter(comments)
            } else {
                (binding.rvCsViewComment.adapter as CsCommentAdapter).updateComments(comments)
            }
        }
        binding.btnCsViewCvConfirmPay.setOnClickListener {
            viewModel.orderEstablished.value?.let { it1 ->
                TapPay.getInstance().prepareGooglePay(
                    requireActivity(),
                    it1.orderId,
                    viewModel.csPayment
                )
            }
        }
    }
}