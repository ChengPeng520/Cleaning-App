package com.example.cleaningapp.customer.chatroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.databinding.ItemFatrueiChatroomTxtBinding

class ClnChatAdapter :
    ListAdapter<ClnChatMessage, ClnChatAdapter.ItemViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemFatrueiChatroomTxtBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(private val itemBinding: ItemFatrueiChatroomTxtBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ClnChatMessage) {
            if (item.customerId != 0) {
                itemBinding.tvChatroomTalkTo.visibility = View.VISIBLE
                itemBinding.tvChatroomTalkTo.text = item.text
            } else {
                itemBinding.tvChatroomTalkFrom.visibility = View.VISIBLE
                itemBinding.tvChatroomTalkFrom.text = item.text
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<ClnChatMessage>() {
        override fun areItemsTheSame(
            oldItem: ClnChatMessage,
            newItem: ClnChatMessage
        ): Boolean {
            return oldItem.msgCustClnId == newItem.msgCustClnId
        }

        override fun areContentsTheSame(
            oldItem: ClnChatMessage,
            newItem: ClnChatMessage
        ): Boolean {
            return oldItem == newItem
        }
    }
}