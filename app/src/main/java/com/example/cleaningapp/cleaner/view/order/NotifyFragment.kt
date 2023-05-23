package com.example.cleaningapp.cleaner.view.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.cleaner.adapter.NotifyAdapter
import com.example.cleaningapp.cleaner.viewmodel.order.NotifyViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiNotifyBinding

class NotifyFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiNotifyBinding
    private val viewModel: NotifyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiNotifyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        initView()
        return binding.root
    }

    private fun initView() {
        with(binding) {
            rvNotify.layoutManager = LinearLayoutManager(requireContext())
            rvNotify.adapter = NotifyAdapter()
            viewModel?.fetchNotify()
            viewModel?.uiState?.observe(requireActivity()) {
                (rvNotify.adapter as NotifyAdapter).submitList(it.notifyItems.toList())
            }
        }
    }
}