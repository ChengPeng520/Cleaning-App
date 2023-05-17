package com.example.cleaningapp.cleaner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.OrderInfoItemUiState
import com.example.cleaningapp.databinding.ItemFatrueiOrderInfoProudctsBinding

class OrderInfoAdapter :
    ListAdapter<OrderInfoItemUiState, OrderInfoAdapter.MyViewHodler>(DiffCallBack()) {
    private lateinit var context: Context

    class DiffCallBack : DiffUtil.ItemCallback<OrderInfoItemUiState>() {
        override fun areItemsTheSame(
            oldItem: OrderInfoItemUiState,
            newItem: OrderInfoItemUiState
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: OrderInfoItemUiState,
            newItem: OrderInfoItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

    class MyViewHodler(private val itemBinding: ItemFatrueiOrderInfoProudctsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(orderInfoItem: OrderInfoItemUiState, context: Context) {
            with(itemBinding) {
                ivOrderInfoImage.setImageResource(orderInfoItem.image)
                tvOrdreInfoName.text = orderInfoItem.name
                tvOrderInfoUnitPrice.text = orderInfoItem.unitPrice.toString()
                tvOrderInfoNumber.text = String.format(
                    context.getString(R.string.tv_shopping_cart_number),
                    orderInfoItem.number
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHodler {
        this.context = parent.context
        return MyViewHodler(
            ItemFatrueiOrderInfoProudctsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHodler, position: Int) {
        holder.onBind(getItem(position), context)
    }
}