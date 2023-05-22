package com.example.cleaningapp.cleaner.view.search

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.Job
import com.example.cleaningapp.cleaner.viewmodel.search.CleanerViewModel
import com.example.cleaningapp.databinding.FragmentVickyCleanerFrontOrderDetailBinding

class CleanerFrontOrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentVickyCleanerFrontOrderDetailBinding
    private val viewModel: CleanerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVickyCleanerFrontOrderDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initAppBarMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            bundle.getSerializable("cleaner")?.let {
                binding.viewModel?.cleaner?.value = it as Job
            }
        }
    }

    private fun initAppBarMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_cleaner_notify, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.notifyFragment -> {
                        Navigation.findNavController(
                            requireActivity(),
                            R.id.cleaner_nav_host_fragment
                        ).navigate(R.id.notifyFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}