package com.example.cleaningapp.customer.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.csCreateOrder.CsCreateOrderViewModel
import com.example.cleaningapp.customer.model.Coupon
import com.example.cleaningapp.databinding.ItemCsPickCouponBinding

class CsCreateOrderCouponUseAdapter (private var coupons: List<Coupon>) :
    RecyclerView.Adapter<CsCreateOrderCouponUseAdapter.CsCouponUserViewHolder>() {

    /**
     * 更新列表內容
     * @param Coupons 新的列表
     */
    fun updateCoupons(coupons: List<Coupon>) {
        this.coupons = coupons
        notifyDataSetChanged()
    }

    class CsCouponUserViewHolder(val itemViewBinding: ItemCsPickCouponBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return coupons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CsCouponUserViewHolder {
        val itemViewBinding = ItemCsPickCouponBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = CsCreateOrderViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CsCouponUserViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: CsCouponUserViewHolder, position: Int) {
        val coupon = coupons[position]
        with(holder) {
            // 將欲顯示的coupon物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.coupon?.value = coupon
//            val bundle = Bundle()
//            bundle.putSerializable("coupon", coupon)
            itemViewBinding.btCusCouponPick.setOnClickListener {
//                Navigation.findNavController(it).navigate(R.id.csCreateOrderFragment,bundle)

                Navigation.findNavController(it).previousBackStackEntry?.savedStateHandle?.set("coupon", coupon)
                Navigation.findNavController(it).popBackStack()
            }
        }
    }
}