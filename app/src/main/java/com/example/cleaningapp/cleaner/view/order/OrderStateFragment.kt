package com.example.cleaningapp.cleaner.view.order

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cleaningapp.cleaner.viewmodel.order.OrderStateViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiOrderStateBinding
import kotlinx.coroutines.launch

class OrderStateFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiOrderStateBinding
    private val viewModel: OrderStateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiOrderStateBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        initView()
        return binding.root
    }

    private fun initView() {
        viewModel.fetchOrderProgress()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    with(binding) {
                        when (it.status) {
                            0 -> {
                                this.tvOrderStateNotStart.visibility = View.VISIBLE
                                this.clOrderStateProgressBar.visibility = View.GONE
                                this.btnOrderStateStartCleaning.visibility = View.VISIBLE
                                this.btnOrderStateStartCleaning.isEnabled = false
                                this.tvOrderStateCancel.visibility = View.VISIBLE
                            }
                            1 -> {
                                this.btnOrderStateStartCleaning.visibility = View.VISIBLE
                                this.btnOrderStateStartCleaning.isEnabled = true
                                this.ivOrderStateApplied.isSelected = true
                                this.tvOrderStateApplied.setTextColor(Color.BLACK)
                            }
                            2 -> {
                                this.btnOrderStateStartCleaning.visibility = View.GONE
                                this.btnOrderStateAddPicture.visibility = View.VISIBLE
                                this.ivOrderStateApplied.isSelected = true
                                this.tvOrderStateApplied.setTextColor(Color.BLACK)
                                this.ivOrderStateIng.isSelected = true
                                this.tvOrderStateIng.setTextColor(Color.BLACK)
                            }
                            3 -> {
                                this.btnOrderStateAddPicture.visibility = View.GONE
                                this.btnOrderStateSign.visibility = View.VISIBLE
                                this.tvOrderStateViewPicture.visibility = View.VISIBLE
                                this.ivOrderStateApplied.isSelected = true
                                this.tvOrderStateApplied.setTextColor(Color.BLACK)
                                this.ivOrderStateIng.isSelected = true
                                this.tvOrderStateIng.setTextColor(Color.BLACK)
                                this.ivOrderStateCheck.isSelected = true
                                this.tvOrderStateCheck.setTextColor(Color.BLACK)
                            }
                            4 -> {
                                this.btnOrderStateSign.visibility = View.GONE
                                this.ivOrderStateApplied.isSelected = true
                                this.tvOrderStateViewPicture.visibility = View.VISIBLE
                                this.tvOrderStateApplied.setTextColor(Color.BLACK)
                                this.ivOrderStateIng.isSelected = true
                                this.tvOrderStateIng.setTextColor(Color.BLACK)
                                this.ivOrderStateCheck.isSelected = true
                                this.tvOrderStateCheck.setTextColor(Color.BLACK)
                                this.ivOrderStateOver.isSelected = true
                                this.tvOrderStateOver.setTextColor(Color.BLACK)
                            }
                        }
                    }
                }
            }
        }
    }
}