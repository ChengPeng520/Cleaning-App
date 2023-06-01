package com.example.cleaningapp.customer.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.ItemVictorOrderboxBinding


class OrderAdapter(private var orders: List<Order>) : RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

    class MyViewHolder(val itemBinding: ItemVictorOrderboxBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(order: Order) {
            when (order.status) {
                1 -> {
                    // 状态为1，表示未完成
                    itemBinding.statusTextView.text = "未完成"
                    itemBinding.statusTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.clr_csTitle))
                }
                2 -> {
                    // 状态为2，表示进行中
                    itemBinding.statusTextView.text = "进行中"
                    itemBinding.statusTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.clr_csTitle))
                }
                3 -> {
                    // 状态为3，表示已完成
                    itemBinding.statusTextView.text = "已完成"
                    itemBinding.statusTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.clr_csTitle))
                }
                else -> {
                    // 其他情况
                    itemBinding.statusTextView.text = "未知状态"
                    itemBinding.statusTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.clr_csTitle))
                }
            }
        }
    }
    fun updateOrders(orders: List<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            ItemVictorOrderboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val orderList = orders[position]
        holder.bind(orderList)
        val bundle = Bundle()
        bundle.putSerializable("orderItem", orderList)
        holder.itemBinding.btnOrderBoxDetailed.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_historicalorderFragment_to_detailedOrderFragment, bundle)
        }
    }
}
