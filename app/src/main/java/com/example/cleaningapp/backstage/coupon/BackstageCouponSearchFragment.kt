package com.example.cleaningapp.backstage.coupon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.coupon.BackstageCouponAdapter
import com.example.cleaningapp.backstage.coupon.BackstageCouponSearchViewModel
import com.example.cleaningapp.databinding.FragmentCiyiCouponSearchBinding

class BackstageCouponSearchFragment : Fragment() {
    private lateinit var binding: FragmentCiyiCouponSearchBinding
    private val viewModel: BackstageCouponSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        requireActivity().setTitle(R.string.menu_backstage_orderManage)
        binding = FragmentCiyiCouponSearchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.menu_backstage_couponsMange)

        binding.couponRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.btCouponAdd.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_backstageCouponSearchFragment_to_backstageCouponCreatFragment)
        }

        viewModel.coupons.observe(viewLifecycleOwner) {
            if (binding.couponRecyclerView.adapter == null) {
                binding.couponRecyclerView.adapter = BackstageCouponAdapter(it)
            } else {
                (binding.couponRecyclerView.adapter as BackstageCouponAdapter).updateCoupons(it)
            }
        }
    }
}