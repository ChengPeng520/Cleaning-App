package com.example.cleaningapp.cleaner.view.order

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.order.OrderStateViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiOrderStateBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class OrderStateFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiOrderStateBinding
    private val viewModel: OrderStateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiOrderStateBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initView()
        setBtnOnclick()
        return binding.root
    }

    private fun initView() {
        viewModel.fetchOrderProgress()
        viewModel.uiState.observe(viewLifecycleOwner) {
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
                        this.tvOrderStateCancel.visibility = View.GONE
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
                        this.ivOrderStateApplied.isSelected = true
                        this.tvOrderStateApplied.setTextColor(Color.BLACK)
                        this.ivOrderStateIng.isSelected = true
                        this.tvOrderStateIng.setTextColor(Color.BLACK)
                        this.ivOrderStateCheck.isSelected = true
                        this.tvOrderStateCheck.setTextColor(Color.BLACK)
                    }
                    4 -> {
                        this.btnOrderStateSign.visibility = View.GONE
                        this.ivOrderStateChatroom.visibility = View.GONE
                        this.ivOrderStateApplied.isSelected = true
                        this.tvOrderStateApplied.setTextColor(Color.BLACK)
                        this.ivOrderStateIng.isSelected = true
                        this.tvOrderStateIng.setTextColor(Color.BLACK)
                        this.ivOrderStateCheck.isSelected = true
                        this.tvOrderStateCheck.setTextColor(Color.BLACK)
                        this.ivOrderStateOver.isSelected = true
                        this.tvOrderStateOver.setTextColor(Color.BLACK)
                    }
                }
                this.ivOrderStateChatroom.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_orderStateFragment_to_cleanerOrderChatroomFragment)
                }
            }
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_cleaner_notify, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return if (menuItem.itemId == R.id.notifyFragment) {
                    Navigation.findNavController(requireActivity(), R.id.cleaner_nav_host_fragment)
                        .navigate(R.id.notifyFragment)
                    true
                } else false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setBtnOnclick() {
        with(binding) {
            btnOrderStateAddPicture.setOnClickListener {
                findNavController().navigate(R.id.action_orderStateFragment_to_vicky_clear_photoFragment)
            }
            btnOrderStateSign.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("orderId", viewModel?.uiState?.value?.orderId!!)
                findNavController().navigate(R.id.action_orderStateFragment_to_vicky_order_cssign_checkFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<BottomNavigationView>(R.id.bvn_cleaner).visibility =
            View.VISIBLE
        viewModel.fetchOrderProgress()
        //接紹片
    }
}