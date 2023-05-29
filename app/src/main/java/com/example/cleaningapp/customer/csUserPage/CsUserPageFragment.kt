package com.example.cleaningapp.customer.csUserPage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.LoginActivity
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentCsUserBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CsUserPageFragment : Fragment() {
    private lateinit var binding:FragmentCsUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CsUserPageViewModel by viewModels()
        binding = FragmentCsUserBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        // 設定lifecycleOwner方能監控LiveData資料變化
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.setTitle(R.string.csTitle_userPage)
        with(binding) {
            btnCsUserPageProfile.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.action_csUserPageFragment_to_csViewProfileFragment)
            }
            btnCsUserPageDiscount.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.action_csUserFragment_to_csViewCouponFragment)
            }
            btnCsUserPageLogOut.setOnClickListener{
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()

            }
        }
    }


}