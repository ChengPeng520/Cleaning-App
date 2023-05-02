package com.example.cleaningapp.backstage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R

class BackstageAccountManageMainDetailFragment : Fragment() {

    companion object {
        fun newInstance() = BackstageAccountManageMainDetailFragment()
    }

    private lateinit var viewModel: BackstageAccountManageMainDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_backstage_account_manage_main_detail,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(BackstageAccountManageMainDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}