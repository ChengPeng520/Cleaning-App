package com.example.cleaningapp.backstage.shop

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.viewModel.BsShopOrderViewModel
import com.example.cleaningapp.databinding.ItemAlbBsShopOrderDataboxBinding

class ShopOrderAdapter(private var shopOrders: List<ShopOrder>) :
    RecyclerView.Adapter<ShopOrderAdapter.OrderViewHolder>() {

    interface  onItemClickListener{
        fun onItemClick(shopOrderId:Int)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateShopOrders(orders: List<ShopOrder>) {
        this.shopOrders = orders
        notifyDataSetChanged()

    }

    class OrderViewHolder(val itemViewBinding: ItemAlbBsShopOrderDataboxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    //建立Ordervieewholder 裡面所要綁定每列order呈現畫面與viewmodel 裡所存在的data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemViewBinding =
            ItemAlbBsShopOrderDataboxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        itemViewBinding.viewModel = BsShopOrderViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return OrderViewHolder(itemViewBinding)


    }

    //綁定每列訂單要呈現 viewholder 與畫面
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = shopOrders[position]
        with(holder) {
            itemViewBinding.viewModel?.shopOrder?.value = order
            val bundle = Bundle()
            order.shopOrderId?.let { bundle.putInt("shopOrderId", it) }
            itemView.setOnClickListener() {
//                order.shopOrderId?.let { it1 -> listener.onItemClick(it1) }
                    Navigation.findNavController(it)
                        .navigate(R.id.action_bsShopOrderFragment_to_bsShopOrderDetailFragment,bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return shopOrders.size

    }


}