package com.example.cleaningapp.backstage.coupon

import android.annotation.SuppressLint
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


    /* CouponViewHolder 有ItemViewBinding的屬性,類型為FragmentCiyiCouponSearchBinding
    這個類別是用於在RecyclerView中顯示每個項目的視圖持有者。

    這段程式碼定義了一個使用Data Binding的項目視圖持有者(CouponViewHolder)，
    它通過接收綁定類別(ItemViewBinding)來輕鬆訪問和操作項目佈局中的元素。
     */
    class CouponViewHolder(val itemViewBinding: ItemCiyiBackstageCouponBinding) :
        ViewHolder(itemViewBinding.root)

    //要訂立一個列表更新的方法
    @SuppressLint("NotifyDataSetChanged")
    fun updateCoupons(coupons: List<Coupon>) {
        this.coupons = coupons
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CouponViewHolder {
        /*創建項目的畫面綁定（ViewBinding）對象,FragmentCiyiCouponSearchBinding是由LayoutInflater.from(畫面擴充來自)parent.context(RecyclerView父容器.內容)*/
        val itemCouponViewBinding = ItemCiyiBackstageCouponBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,                 //parent：這是RecyclerView的父容器，即RecyclerView所在的布局容器。在創建ViewHolder時，我們需要提供父容器的參考，以便在後續的佈局中使用。
            false       //這是將創建的ViewHolder加入到父容器中的一個標誌。當設置為false時，表示不將ViewHolder自動添加到父容器中。
        )
        itemCouponViewBinding.viewModel = BackstageCouponViewModel()    //跟單一筆的couponViewModel 畫面資料綁定
        itemCouponViewBinding.lifecycleOwner =
            parent.findViewTreeLifecycleOwner() //將ViewModel和生命周期所有者（LifecycleOwner）與項目視圖綁定對象關聯起來，以便在該項目的布局中使用ViewModel的數據綁定。

        return CouponViewHolder(itemCouponViewBinding)

    }

    override fun onBindViewHolder(
        holder: CouponViewHolder,
        position: Int,
    ) {
        val coupon = coupons[position]
        holder.itemViewBinding.viewModel?.coupon?.value = coupon
        holder.itemViewBinding.btnCouponEdit.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_backstageCouponSearchFragment_to_backstageCouponModifyFragment)
        }
    }


    override fun getItemCount(): Int {
        return coupons.size
    }
}