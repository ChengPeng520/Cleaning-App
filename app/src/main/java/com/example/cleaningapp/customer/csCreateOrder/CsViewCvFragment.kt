package com.example.cleaningapp.customer.csCreateOrder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.adapter.CsCommentAdapter
import com.example.cleaningapp.databinding.FragmentCsViewCvBinding
import com.example.cleaningapp.share.TapPay

class CsViewCvFragment : Fragment() {
    private lateinit var binding: FragmentCsViewCvBinding
    private val viewModel: CsViewCvViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCsViewCvBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.csTitle_viewCv)
        arguments?.let { bundle ->
            bundle.getInt("cleanerId").let {
                viewModel.fetchCleanerInfo(it)
                viewModel.fetchComments(it)
                viewModel.orderEstablished.value?.cleanerId = it
                Log.d("xxx", "cleanerId:$it")
            }
            bundle.getInt("orderId").let {
                viewModel.fetchOrdersInfo(it)
                viewModel.orderEstablished.value?.orderId = it
                Log.d("xxx", "orderId: $it")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100) {
            viewModel.checkout(requireContext())
        }
    }
}