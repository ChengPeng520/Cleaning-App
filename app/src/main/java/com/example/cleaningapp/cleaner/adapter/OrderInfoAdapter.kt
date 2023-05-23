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
    ListAdapter<OrderInfoItemUiState, OrderInfoAdapter.ItemViewHodler>(DiffCallBack()) {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHodler {
        this.context = parent.context
        return ItemViewHodler(
            ItemFatrueiOrderInfoProudctsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHodler, position: Int) {
        holder.onBind(getItem(position), context)
    }

    class ItemViewHodler(private val itemBinding: ItemFatrueiOrderInfoProudctsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(orderInfoItem: OrderInfoItemUiState, context: Context) {
            with(itemBinding) {
                ivOrderInfoImage.setImageResource(orderInfoItem.image)
                tvOrdreInfoName.text = orderInfoItem.name
                tvOrderInfoUnitPrice.text = orderInfoItem.unitPrice.toString()
                tvOrderInfoNumber.text = String.format(
                    context.getString(R.string.tv_shopping_info_number),
                    orderInfoItem.number
                )
            }
        }
    }

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
}