package com.example.cleaningapp.backstage.coupon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.ItemCiyiBackstageCouponBinding

/*建立一個可以顯示Coupon的data與viewModle的RecycleView容器BackstageCouponAdapter,
類別var coupons是要用來儲存Coupon列表資料,這個BackstageCouponAdapter是繼承RecyclerView.Adapter
<BackstageCouponAdapter.CouponViewHolder>成為RecyclerViewk的適配器,
並使用自訂的CouponViewHolder類別class */

class BackstageCouponAdapter(private var coupons: List<Coupon>) :
    RecyclerView.Adapter<BackstageCouponAdapter.CouponViewHolder>() {


    class CouponViewHolder(val itemViewBinding: ItemCiyiBackstageCouponBinding) :
        ViewHolder(itemViewBinding.root)

    //要訂立一個列表更新的方法
    @SuppressLint("NotifyDataSetChanged")
    fun updateCoupons(coupons: List<Coupon>) {
        this.coupons = coupons
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
        val itemCouponViewBinding = ItemCiyiBackstageCouponBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemCouponViewBinding.viewModel =
            BackstageCouponViewModel()
        itemCouponViewBinding.lifecycleOwner =
            parent.findViewTreeLifecycleOwner()
        return CouponViewHolder(itemCouponViewBinding)
    }

    override fun onBindViewHolder(
        holder: CouponViewHolder,
        position: Int,
    ) {
        val coupon = coupons[position]
        with(holder) {
            itemViewBinding.viewModel?.coupon?.value = coupon
            val bundle = Bundle()
            bundle.putInt("couponId", coupon.couponId)
            itemView.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_backstageCouponSearchFragment_to_BackstageCouponModifyFragment,
                    bundle
                )
            }
        }
    }


    override fun getItemCount(): Int {
        return coupons.size
    }
}