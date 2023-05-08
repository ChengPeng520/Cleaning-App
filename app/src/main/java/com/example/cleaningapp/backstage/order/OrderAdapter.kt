package com.example.cleaningapp.backstage.order

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.databinding.ItemCiyiBackstageOrderBinding

/**
 * 訂單列表所需的Adapter
 */
class OrderAdapter(private var orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>(){

/**
 * 更新訂單列表內容
 * @param orders 新的訂單列表
 */
  //建立一個更新訂單列表的方法
  fun updateOrders(orders: List<Order>){
    this.orders=orders
    notifyDataSetChanged()
  }
      class OrderViewHolder(itemViewBinding: ItemCiyiBackstageOrderBinding):
              RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}