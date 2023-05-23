package com.example.cleaningapp.customer.csCreateOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.adapter.CsCommentAdapter
import com.example.cleaningapp.databinding.FragmentCsViewCvBinding


class CsViewCvFragment : Fragment() {
    private lateinit var binding: FragmentCsViewCvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsViewCvViewModel by viewModels()
        binding = FragmentCsViewCvBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            bundle.getSerializable("cleaner")?.let {
                binding.viewModel?.cleaner?.value = it as Cleaner
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
            Navigation.findNavController(it)
                .navigate(R.id.action_csViewCvFragment_to_paymentFragment)
        }
    }
}