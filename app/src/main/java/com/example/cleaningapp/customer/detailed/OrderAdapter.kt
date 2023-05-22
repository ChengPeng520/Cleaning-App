package com.example.cleaningapp.customer.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.ItemVictorOrderboxBinding

class OrderAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

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
        return orders.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val orderList = orders[position]
        holder.itemBinding.viewModel?.orderItem?.value = orderList
        val bundle = Bundle()
        bundle.putSerializable("orderItem", orderList)
        holder.itemBinding.btnOrderBoxDetailed.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_historicalorderFragment_to_detailedOrderFragment, bundle)
        }
    }
}