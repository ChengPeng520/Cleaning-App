package com.example.cleaningapp.cleaner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.cleaner.uistate.OrderHistoryItemUiState
import com.example.cleaningapp.databinding.ItemFatrueiOrderHistoryOrderBinding

class OrderHistoryAdapter :
    ListAdapter<OrderHistoryItemUiState, OrderHistoryAdapter.MyViewHolder>(DiffCallBack()) {
    class DiffCallBack : DiffUtil.ItemCallback<OrderHistoryItemUiState>() {
        override fun areItemsTheSame(
            oldItem: OrderHistoryItemUiState,
            newItem: OrderHistoryItemUiState
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: OrderHistoryItemUiState,
            newItem: OrderHistoryItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

    class MyViewHolder(private val itemBinding: ItemFatrueiOrderHistoryOrderBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(orderHistoryItem: OrderHistoryItemUiState) {
            with(itemBinding) {
                tvOrderHistoryReceiptDate.text = orderHistoryItem.date
                tvOrderHistoryReceiptCount.text = orderHistoryItem.totalCount.toString()
                ivOrderHistoryImage.setImageResource(orderHistoryItem.image)
                tvOrderHistoryName.text = orderHistoryItem.name
                tvOrderHistoryUnitPrice.text = orderHistoryItem.unitPrice.toString()
                tvOrderHistoryNumber.text = orderHistoryItem.number.toString()
                tvOrderHistoryGrossPrice.text = orderHistoryItem.grossPrice.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemFatrueiOrderHistoryOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}