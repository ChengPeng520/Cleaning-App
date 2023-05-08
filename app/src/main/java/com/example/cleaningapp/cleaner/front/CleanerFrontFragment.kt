package com.example.cleaningapp.cleaner.front

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cleaningapp.CleanerFrontViewModel
import com.example.cleaningapp.databinding.FragmentVickyCleanerFrontBinding

class CleanerFrontFragment : Fragment() {
    private lateinit var binding: FragmentVickyCleanerFrontBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel:CleanerFrontViewModel by viewModels()
        binding = FragmentVickyCleanerFrontBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        with(binding) {
//            RecyclerView.layoutManager = LinearLayoutManager(requireContext())
//            viewModel?.cleaner?.observe(viewLifecycleOwner) { cleaners ->
//                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
//                if (recyclerView.adapter == null) {
//                    recyclerView.adapter = CleanerAdapter(cleaners)
//                } else {
//                    (recyclerView.adapter as CleanerAdapter).updateCleaners(cleaners)
//                }
//            }
//        }
//        }
}


