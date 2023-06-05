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


class OrderAdapter(private var orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {
    class MyViewHolder(val itemBinding: ItemVictorOrderboxBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(order: Order) {
            when (order.status) {
                0 -> {
                    // 状态为1，表示徵才中
                    itemBinding.statusTextView.text = "徵才中"
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_csTitle
                        )
                    )
                    itemBinding.statusTextView.setBackgroundResource(R.drawable.victor_order_status_black)
                }
                1 -> {
                    // 状态为1，表示接單成功
                    itemBinding.statusTextView.text = "接單成功"
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_cusCoupon_deadline
                        )
                    )
                    itemBinding.statusTextView.setBackgroundResource(R.drawable.victor_order_status_red)
                }
                2 -> {
                    // 状态为2，表示進行中
                    itemBinding.statusTextView.text = "進行中"
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_cusCoupon_deadline
                        )
                    )
                    itemBinding.statusTextView.setBackgroundResource(R.drawable.victor_order_status_red)
                }
                3 -> {
                    // 状态为3，表示打掃結束
                    itemBinding.statusTextView.text = "打掃結束"
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_cusCoupon_deadline
                        )
                    )
                    itemBinding.statusTextView.setBackgroundResource(R.drawable.victor_order_status_red)
                }
                4 -> {
                    // 状态为4，表示顧客確認
                    itemBinding.statusTextView.text = "顧客確認"
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_cusCoupon_deadline
                        )
                    )
                    itemBinding.statusTextView.setBackgroundResource(R.drawable.victor_order_status_red)
                }
                5 -> {
                    // 状态为5，表示已完成
                    itemBinding.statusTextView.text = "已完成"
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_cusCoupon_deadline
                        )
                    )
                    itemBinding.statusTextView.setBackgroundResource(R.drawable.victor_order_status_red)
                }
                6 -> {
                    // 状态为6，表示退費申請中
                    itemBinding.statusTextView.text = "退費申請中"
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_cusCoupon_deadline
                        )
                    )
                    itemBinding.statusTextView.setBackgroundResource(R.drawable.victor_order_status_red)
                }
                7 -> {
                    // 状态为7，表示退費核可
                    itemBinding.statusTextView.text = "退費核可"
                    itemBinding.statusTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.clr_cusCoupon_deadline
                        )
                    )
                    itemBinding.statusTextView.setBackgroundResource(R.drawable.victor_order_status_red)
                }
                8 -> {
                    // 状态为8，表示已退款
                    itemBinding.statusTextView.text = "已退款"
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
        // 设置按钮点击事件和导航目的地
        holder.itemBinding.btnOrderBoxDetailed.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("orderItem", orderList)

            when (orderList.status) {
                0 -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_historicalorderFragment_to_csChooseCleanerFragment2, bundle)
                }
                1 -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.orderprogressFragment, bundle)
                }
                2 -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.orderingFragment, bundle)
                }
                3 -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.orderdoneFragment, bundle)
                }
                4 -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.ordercompletedFragment, bundle)
                }
                5 -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_historicalorderFragment_to_detailedOrderFragment, bundle)
                }
                6 -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.orderChatroomFragment, bundle)
                }
                7 -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.orderChatroomFragment, bundle)
                }
                8 -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.orderChatroomFragment, bundle)
                }
            }
        }
    }
}
