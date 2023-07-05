package com.example.cleaningapp.backstage.usermanage.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.backstage.usermanage.model.ChatItem
import com.example.cleaningapp.databinding.ItemAlbBsUserServChatTxtBinding

class UserServiceChatAdapter :
    ListAdapter<ChatItem, UserServiceChatAdapter.ItemViewHolder>(DiffCallBack()) {
    private var layoutWidth: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        this.layoutWidth = parent.width / 3 * 2
        return ItemViewHolder(
            ItemAlbBsUserServChatTxtBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), layoutWidth)
    }

    class ItemViewHolder(private val itemBinding: ItemAlbBsUserServChatTxtBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ChatItem, layoutWidth: Int) {
            if (item.customerId == 0 && item.cleanerId == 0) {
                itemBinding.tvChatroomTalkTo.visibility = View.VISIBLE
                itemBinding.tvChatroomTalkTo.text = item.text
                itemBinding.tvChatroomTalkTo.maxWidth = layoutWidth
            } else {
                itemBinding.tvChatroomTalkFrom.visibility = View.VISIBLE
                itemBinding.tvChatroomTalkFrom.text = item.text
                itemBinding.tvChatroomTalkFrom.maxWidth = layoutWidth
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<ChatItem>() {
        override fun areItemsTheSame(
            oldItem: ChatItem,
            newItem: ChatItem
        ): Boolean {
            return (oldItem.msgCustBackId == newItem.msgCustBackId) || (oldItem.msgClnBackId == newItem.msgClnBackId)
        }

        override fun areContentsTheSame(
            oldItem: ChatItem,
            newItem: ChatItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}