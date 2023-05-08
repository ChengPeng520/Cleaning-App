package com.example.cleaningapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class vicky_clear_photoFragment : Fragment() {

    companion object {
        fun newInstance() = vicky_clear_photoFragment()
    }

    private lateinit var viewModel: VickyClearPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vicky_clear_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VickyClearPhotoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}