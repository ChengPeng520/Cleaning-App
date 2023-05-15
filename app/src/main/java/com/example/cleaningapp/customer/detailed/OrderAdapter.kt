package com.example.cleaningapp.customer.detailed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.databinding.ItemVictorOrderboxBinding

class OrderAdapter(private val orderList: List<Order>) : RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

    class MyViewHolder(val itemBinding: ItemVictorOrderboxBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            ItemVictorOrderboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemView.viewModel = OrderListViewModel()
        itemView.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.viewModel?.orderItem?.value = orderList[position]
    }
}