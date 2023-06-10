package com.example.cleaningapp.customer.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.ItemVictorOrderboxBinding


class OrderAdapter(private var orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

    inner class MyViewHolder(val itemBinding: ItemVictorOrderboxBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(order: Order) {
            // 设置状态
            when (order.status) {
                0 -> {
                    // 状态为0，表示徵才中
                    itemBinding.statusTextView.text = "徵才中"
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_csTitle
                        )
                    )
                    itemBinding.statusTextView.setBackgroundResource(R.drawable.victor_order_status_black)
                }
                1, 2, 3, 4, 5, 6, 7 -> {
                    // 状态为1、2、3、4、5、6、7，表示其他状态
                    itemBinding.statusTextView.text = getStatusText(order.status)
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_cusCoupon_deadline
                        )
                    )
                    itemBinding.statusTextView.setBackgroundResource(R.drawable.victor_order_status_red)
                }
                else -> {
                    // 其他情况
                    itemBinding.statusTextView.text = "未知状态"
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_csTitle
                        )
                    )
                }
            }

            itemBinding.tvOrderOrderId.text = order.orderId.toString()
            itemBinding.tvOrderCleaner.text = order.cleanerName
            itemBinding.tvOrderDate.text = order.dateOrdered.toString()
            itemBinding.tvOrderRange.text = order.cleaningRange
            itemBinding.tvOrderTime.text = order.cleaningTime
            itemBinding.tvOrderTotal.text = order.priceForCustomer.toString()
        }

        private fun getStatusText(status: Int): String {
            return when (status) {
                1 -> "媒合成功"
                2 -> "正進行中"
                3 -> "顧客確認"
                4 -> "打掃結束"
                5 -> "已完成"
                6 -> "已取消"
                7 -> "客訴完成"
                else -> "未知状态"
            }
        }
    }

    fun updateOrders(orders: List<Order>) {
        this.orders = orders
        notifyDataSetChanged()
//        Log.d("OrderAdapter", "Updated orders: $orders")
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
        //判斷狀態更改Button文字
        val btnOrderBoxDetailed = holder.itemBinding.btnOrderBoxDetailed
        when (orderList.status) {
            0 -> btnOrderBoxDetailed.setText(R.string.btn_orderBox_choose)
            1, 2, 3, 4, 5, 6, 7 ->
                btnOrderBoxDetailed.setText(R.string.btn_orderBox_detailed)
        }
        // 设置按钮点击事件和导航目的地
        holder.itemBinding.btnOrderBoxDetailed.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("orderId", orderList.orderId)
            val navController = holder.itemView.findNavController()
            when (orderList.status) {
                0 -> navController.navigate(
                    R.id.action_historicalorderFragment_to_csChooseCleanerFragment2, bundle
                )
                1 -> navController.navigate(R.id.orderprogressFragment, bundle)
                2 -> navController.navigate(R.id.orderingFragment, bundle)
                3 -> navController.navigate(R.id.ordercompletedFragment, bundle)
                4 -> navController.navigate(R.id.orderdoneFragment, bundle)
                5 -> navController.navigate(
                    R.id.action_historicalorderFragment_to_detailedOrderFragment, bundle
                )
                6 -> navController.navigate(R.id.orderChatroomFragment, bundle)
                7 -> navController.navigate(R.id.complaintdetailsFragment, bundle)
            }
        }
    }
}