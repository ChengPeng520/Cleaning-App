package com.example.cleaningapp.customer.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.csHomePage.CsCouponObtainViewModel
import com.example.cleaningapp.customer.model.Coupon
import com.example.cleaningapp.databinding.ItemCsObtainCouponBinding

class CsCouponObtainAdapter(private var coupons: List<Coupon>) :
    RecyclerView.Adapter<CsCouponObtainAdapter.CouponObtainViewHolder>() {

    /**
     * 更新列表內容
     * @param Coupons 新的列表
     */
    fun updateCoupons(coupons: List<Coupon>) {
        this.coupons = coupons
        notifyDataSetChanged()
    }

    class CouponObtainViewHolder(val itemViewBinding: ItemCsObtainCouponBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return coupons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponObtainViewHolder {
        val itemViewBinding = ItemCsObtainCouponBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewmodel = CsCouponObtainViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CouponObtainViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: CouponObtainViewHolder, position: Int) {
        val coupon = coupons[position]
        with(holder) {
            // 將欲顯示的coupon物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewmodel?.coupon?.value = coupon
            val bundle = Bundle()
            bundle.putSerializable("coupon", coupon)
            itemViewBinding.btCusCouponObtain.setOnClickListener() {
                Toast.makeText(it.context, it.context.getString(R.string.toast_csHome_obtainSucceed), Toast.LENGTH_SHORT).show()
            }
        }
    }
}