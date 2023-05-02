package com.example.cleaningapp.backstage.usermanage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleaningapp.R

class BackstageAccountManageMainFragment : Fragment() {

    companion object {
        fun newInstance() = BackstageAccountManageMainFragment()
    }

    private lateinit var viewModel: BackstageAccountManageMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alb_backstage_account_manage_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BackstageAccountManageMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}