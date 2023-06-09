package com.example.cleaningapp.cleaner.view.shop

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.adapter.MallAdapter
import com.example.cleaningapp.cleaner.viewmodel.shop.MallViewModel
import com.example.cleaningapp.databinding.FragmentFatrueiMallBinding
import kotlinx.coroutines.launch

class MallFragment : Fragment() {
    private lateinit var binding: FragmentFatrueiMallBinding
    private val viewModel: MallViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFatrueiMallBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initAppBarMenu()
        initRecycler()
        onSearchInputChange()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.cleaner_toolbar_title).text = "商城"
    }

    private fun initRecycler() {
        with(binding) {
            rvMallProducts.layoutManager = GridLayoutManager(requireContext(), 2)
            rvMallProducts.adapter = MallAdapter()
            viewModel?.fetchProducts()
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel?.uiState?.collect {
                        (rvMallProducts.adapter as MallAdapter).submitList(it.productItems)
                    }
                }
            }
        }
    }

    private fun onSearchInputChange() {
        binding.svMallSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchInputChange(newText)
                return true
            }
        })
    }

    private fun initAppBarMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_cleaner_order_history, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.order_history) {
                    Navigation.findNavController(requireActivity(), R.id.cleaner_nav_host_fragment)
                        .navigate(R.id.action_shopFragment_to_orderHistoryFragment)
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}