package com.example.cleaningapp.customer.detailed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.databinding.ItemVictorOrderboxBinding

class OrderAdapter(val orderList: List<Order>) : RecyclerView.Adapter<OrderAdapter.MyViewHoder>() {

    class MyViewHoder(val itemBinding: ItemVictorOrderboxBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHoder {
        val itemView = ItemVictorOrderboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemView.viewModel = orederListViewModel()
        itemView.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MyViewHoder(itemView)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: MyViewHoder, position: Int) {
        holder.itemBinding.viewModel?.orderItem?.value = orderList[position]
    }
}