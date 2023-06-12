package com.example.cleaningapp.cleaner.view.search

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.viewmodel.search.CleanerFrontOrderDetailViewModel
import com.example.cleaningapp.databinding.FragmentVickyCleanerFrontOrderDetailBinding

class CleanerFrontOrderDetailFragment : Fragment() {
    private lateinit var binding: FragmentVickyCleanerFrontOrderDetailBinding
    private val viewModel: CleanerFrontOrderDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVickyCleanerFrontOrderDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.cleaner_toolbar_title).text = "訂單詳情"
        with(binding) {
            // 訂單詳情
            arguments?.getInt("orderId")?.let {
                viewModel?.fetchOrderAccept(it)
            }
            arguments?.getByteArray("photo")?.let {
                val job = viewModel?.job?.value
                job?.photo = it
                viewModel?.job?.value = job
            }
            textView31.setOnClickListener {
                val address = binding.textView31.text.toString()
                googleMaps(address)
            }
        }
    }

    private fun googleMaps(address: String) {
        val intentUri = Uri.parse("google.navigation:q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}