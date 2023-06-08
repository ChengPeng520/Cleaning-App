package com.example.cleaningapp.backstage.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.ItemCiyiBackstageOrderBinding


/**
 * 訂單列表所需的Adapter
 */
class OrderAdapter(private var orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    /**
     * 更新訂單列表內容
     * @param orders 新的好友列表
     */
    //建立一個更新訂單列表的方法
    @SuppressLint("NotifyDataSetChanged")
    fun updateOrders(orders: List<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    class OrderViewHolder(val itemViewBinding: ItemCiyiBackstageOrderBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemViewBinding = ItemCiyiBackstageOrderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = OrderViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return OrderViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        with(holder) {
            val bundle = Bundle()
            order.orderId?.let { bundle.putInt("orderId", it) }
            itemViewBinding.viewModel?.order?.value = order
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(
                        R.id.backstageOrderDetailFragment, bundle
                    )
            }
        }
    }
}