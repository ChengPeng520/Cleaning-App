package com.example.cleaningapp.cleaner.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.Work
import com.example.cleaningapp.cleaner.viewmodel.order.OrdersViewModel

import com.example.cleaningapp.cleaner.viewmodel.search.CleanerViewModel
import com.example.cleaningapp.databinding.ItemVickyCleanerFrontBinding
import com.example.cleaningapp.databinding.ItemVickyCleanerOrderConductBinding

class OrderAdapter(private var orders: List<Work>) :
    RecyclerView.Adapter<OrderAdapter.OrdersViewHolder>() {

    fun updateOrders(orders: List<Work>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    class OrdersViewHolder(val itemViewBinding: ItemVickyCleanerOrderConductBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val itemViewBinding = ItemVickyCleanerOrderConductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = OrdersViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return OrdersViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val order = orders[position]
        //position 現在生成位子
        with(holder) {
            // 將欲顯示的cleaner物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.order?.value = order
            val bundle = Bundle()
            bundle.putSerializable("orders", order)
            itemView.setOnClickListener {

            }
        }
    }
}

