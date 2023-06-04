package com.example.cleaningapp.customer.csHomePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.adapter.CsHomePageCommentAdapter
import com.example.cleaningapp.databinding.FragmentCsHomepageBinding

class CsHomePageFragment : Fragment() {
    private lateinit var binding: FragmentCsHomepageBinding
    val viewModel: CsHomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCsHomepageBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.csTitle_homepage)
        with(binding) {
            flCsHomeCoupon.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_csHomePageFragment_to_csCouponObtainFragment)
            }
        }

        viewModel.fetchCleaners()
        binding.rvCsHomePageComment.layoutManager = LinearLayoutManager(requireContext())
        viewModel?.cleaners?.observe(viewLifecycleOwner) { cleaners ->
            if (binding.rvCsHomePageComment.adapter == null) {
                binding.rvCsHomePageComment.adapter = CsHomePageCommentAdapter(cleaners)
            } else {
                (binding.rvCsHomePageComment.adapter as CsHomePageCommentAdapter).updateCleaners(cleaners)
            }
        }
    }
}