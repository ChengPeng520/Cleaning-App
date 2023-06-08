package com.example.cleaningapp.cleaner.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.cleaner.viewmodel.order.OrdersViewModel
import com.example.cleaningapp.databinding.ItemVickyCleanerOrderConductBinding

class OrderAdapter(var orders: List<SearchOrder>) :
    RecyclerView.Adapter<OrderAdapter.OrdersViewHolder>() {

    class OrdersViewHolder(val itemViewBinding: ItemVickyCleanerOrderConductBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    fun updateOrders(orders: List<SearchOrder>) {
        this.orders = orders
        notifyDataSetChanged()
    }

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
            bundle.putInt("orderId", order.orderId)
            itemView.setOnClickListener {
                when (order.status) {
                    // 待確認
                    0 -> {
                        Navigation.findNavController(it)
                            .navigate(
                                R.id.action_vicky_order_conductFragment_to_vicky_order_ordermatchFragment,
                                bundle
                            )
                    }
                    // 已完成
                    4 -> {
                        Navigation.findNavController(it)
                            .navigate(
                                R.id.action_vicky_order_conductFragment_to_completeOrderInfoFragment,
                                bundle
                            )
                    }
                    5 -> {
                        Navigation.findNavController(it)
                            .navigate(
                                R.id.action_vicky_order_conductFragment_to_completeOrderInfoFragment,
                                bundle
                            )
                    }
                    7 -> {
                        Navigation.findNavController(it)
                            .navigate(
                                R.id.action_vicky_order_conductFragment_to_completeOrderInfoFragment,
                                bundle
                            )
                    }
                    // 1 已成立 2 進行中 3
                    else -> {
                        Navigation.findNavController(it)
                            .navigate(
                                R.id.action_vicky_order_conductFragment_to_orderStateFragment,
                                bundle
                            )
                    }
                }
            }
        }
    }
}