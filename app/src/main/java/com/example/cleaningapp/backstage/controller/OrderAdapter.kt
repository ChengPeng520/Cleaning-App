package com.example.cleaningapp.backstage.controller

import androidx.annotation.ReturnThis
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.backstage.model.Order
import com.example.cleaningapp.databinding.BackstageOrderItemviewBinding

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
      class OrderViewHolder(val itemViewBinding: BackstageOrderItemviewBinding):
              RecyclerView.ViewHolder(itemViewBinding.root)
}