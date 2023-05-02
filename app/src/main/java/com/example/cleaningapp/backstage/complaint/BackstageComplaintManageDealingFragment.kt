package com.example.cleaningapp.backstage.complaint

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R

class BackstageComplaintManageDealingFragment : Fragment() {

    companion object {
        fun newInstance() = BackstageComplaintManageDealingFragment()
    }

    private lateinit var viewModel: BackstageComplaintManageDealingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_alb_backstage_complaint_manage_dealing,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(BackstageComplaintManageDealingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}