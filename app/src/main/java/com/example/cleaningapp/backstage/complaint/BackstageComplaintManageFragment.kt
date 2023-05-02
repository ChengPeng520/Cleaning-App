package com.example.cleaningapp.backstage.complaint

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R

class BackstageComplaintManageFragment : Fragment() {

    companion object {
        fun newInstance() = BackstageComplaintManageFragment()
    }

    private lateinit var viewModel: BackstageComplaintManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alb_backstage_complaint_manage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BackstageComplaintManageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}