package com.example.cleaningapp.cleaner.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.OrderHistoryItemUiState
import com.example.cleaningapp.databinding.ItemFatrueiOrderHistoryOrderBinding

class OrderHistoryAdapter :
    ListAdapter<OrderHistoryItemUiState, OrderHistoryAdapter.ItemViewHolder>(DiffCallBack()) {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        this.context = parent.context
        return ItemViewHolder(
            ItemFatrueiOrderHistoryOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position), context)
    }

    class ItemViewHolder(private val itemBinding: ItemFatrueiOrderHistoryOrderBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(orderHistoryItem: OrderHistoryItemUiState, context: Context) {
            with(itemBinding) {
                tvOrderHistoryReceiptDate.text = orderHistoryItem.orderTime
                tvOrderHistoryReceiptState.text =
                    if (orderHistoryItem.isDelivered) "已送達" else if (orderHistoryItem.isShipped) "已出貨" else "以結帳"
                tvOrderHistoryReceiptCount.text = orderHistoryItem.totalCount.toString()
                ivOrderHistoryImage.setImageBitmap(orderHistoryItem.productPhoto)
                tvOrderHistoryName.text = orderHistoryItem.name
                tvOrderHistoryUnitPrice.text = orderHistoryItem.unitPrice.toString()
                tvOrderHistoryNumber.text = String.format(
                    context.getString(R.string.tv_shopping_info_number),
                    orderHistoryItem.number
                )
                tvOrderHistoryGrossPrice.text = String.format(
                    context.getString(
                        R.string.tv_shopping_info_gross_price,
                        orderHistoryItem.grossPrice
                    )
                )
                clOrderHistory.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt("shopOrderId", orderHistoryItem.id)
                    bundle.putSerializable("orderHistoryItem", orderHistoryItem)
                    Navigation.findNavController(it)
                        .navigate(R.id.action_orderHistoryFragment_to_orderInfoFragment, bundle)
                }
            }
        }
    }

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
}