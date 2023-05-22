package com.example.cleaningapp.backstage.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.shop.viewModel.BsShopOrderViewModel
import com.example.cleaningapp.databinding.ItemAlbBsShopOrderDataboxBinding

class ShopOrderAdapter(private var shopOrders: List<shopOrder>) :
    RecyclerView.Adapter<ShopOrderAdapter.OrderViewHolder>() {

    //更新資料列表內需要oderlist ,寫出這個方法內的oders 是哪邊的oders
    fun updateShopOrders(orders: List<shopOrder>) {
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
        val order = shopOrders[position]  //首先通过position从shopOrders列表中获取对应位置的订单数据，将其赋值给order变量
        with(holder) {
            itemViewBinding.viewModel?.shopOrder?.value = order
            val bundle = Bundle()
            bundle.putSerializable("order", order)

            itemView.setOnClickListener() {
                Navigation.findNavController(it)
                    .navigate(R.id.action_bsShopOrderFragment_to_bsShopOrderDetailFragment)
            }
        }


    }

    override fun getItemCount(): Int {
        return shopOrders.size

    }


}