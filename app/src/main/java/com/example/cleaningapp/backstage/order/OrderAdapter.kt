package com.example.cleaningapp.backstage.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.ItemCiyiBackstageOrderBinding


/**
 * 訂單列表所需的Adapter
 */
class OrderAdapter(private var orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    /**
     * 更新訂單列表內容
     * @param orders 新的好友列表
     */
    //建立一個更新訂單列表的方法
    @SuppressLint("NotifyDataSetChanged")
    fun updateOrders(orders: List<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    class OrderViewHolder(val itemViewBinding: ItemCiyiBackstageOrderBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemViewBinding = ItemCiyiBackstageOrderBinding.inflate(
            LayoutInflater.from
                (parent.context), parent, false
        )
        itemViewBinding.viewModel = OrderViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return OrderViewHolder(itemViewBinding)
    }


    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]   //這行程式碼從orders列表中獲取指定位置（position）的訂單資料，並將其存儲在order變數中。
        with(holder) {                    //允許在holder對象的作用域內直接訪問其中的屬性和方法。
            itemViewBinding.viewModel?.order?.value = order
            val bundle = Bundle()
            //序列化bundle的(key,value) = ("order",order)
            bundle.putSerializable("order", order)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(
                        R.id.action_orderManageFragment2_to_backstageOrderDetailFragment,
                        bundle
                    )
            }


        }
    }
}