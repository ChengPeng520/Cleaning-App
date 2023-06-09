package com.example.cleaningapp.customer.csUserPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.LoginActivity
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCsUserBinding

class CsUserPageFragment : Fragment() {
    private lateinit var binding: FragmentCsUserBinding
    private val viewModel: CsUserPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCsUserBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text =
            getString(R.string.csTitle_userPage)
        with(binding) {
            llCsUserUserInfo.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_csUserPageFragment_to_csEditProfileFragment)
            }
            llCsUserViewCoupon.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_csUserFragment_to_csViewCouponFragment)
            }
            btnCsUserLogout.setOnClickListener {
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPhoto()
    }
}