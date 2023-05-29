package com.example.cleaningapp.customer.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.viewModel.ComplaintdetailsViewModel
import com.example.cleaningapp.databinding.FragmentVictorComplaintdetailsBinding

class ComplaintdetailsFragment : Fragment() {
    private lateinit var binding: FragmentVictorComplaintdetailsBinding
    val viewModel: ComplaintdetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorComplaintdetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            bundle.getSerializable("orderItem")?.let {
                viewModel.order.value = it as Order
            }

            val photoUri = bundle.getParcelable<Uri>("photoUri")
            photoUri?.let {
                viewModel.photoUri.value = it
            }
        }

        viewModel.photoUri.observe(viewLifecycleOwner) { uri ->
            // 設置照片到對應的 ImageView
            when (viewModel.capturedCount) {
                1 -> binding.imageView41.setImageURI(uri)
                2 -> binding.imageView42.setImageURI(uri)
                3 -> binding.imageView40.setImageURI(uri)
            }
        }
        with(binding){
            bntApplyComplaintChat.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_complaintdetailsFragment_to_chatRoomFragment)
            }
        }
    }
}