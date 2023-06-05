package com.example.cleaningapp.customer.chatroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.databinding.ItemFatrueiChatroomTxtBinding

class ChatAdapter :
    ListAdapter<ChatroomItemUiState, ChatAdapter.ItemViewHodler>(DiffCallBack()) {
        private var layoutWidth: Int = 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHodler {
            this.layoutWidth = parent.width / 3 * 2
            return ItemViewHodler(
                ItemFatrueiChatroomTxtBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ItemViewHodler, position: Int) {
            holder.bind(getItem(position), layoutWidth)
        }

        class ItemViewHodler(private val itemBinding: ItemFatrueiChatroomTxtBinding) :
            RecyclerView.ViewHolder(itemBinding.root) {
            fun bind(item: ChatroomItemUiState, layoutWidth: Int) {
                if (item.fromId == 0) {
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

        class DiffCallBack : DiffUtil.ItemCallback<ChatroomItemUiState>() {
            override fun areItemsTheSame(
                oldItem: ChatroomItemUiState,
                newItem: ChatroomItemUiState
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ChatroomItemUiState,
                newItem: ChatroomItemUiState
            ): Boolean {
                return oldItem == newItem
            }
        }
    }