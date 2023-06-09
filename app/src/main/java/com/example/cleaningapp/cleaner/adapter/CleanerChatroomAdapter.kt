package com.example.cleaningapp.cleaner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.cleaner.uistate.ChatroomItemUiState
import com.example.cleaningapp.databinding.ItemFatrueiChatroomTxtBinding

class CleanerChatroomAdapter :
    ListAdapter<ChatroomItemUiState, CleanerChatroomAdapter.ItemViewHolder>(DiffCallBack()) {
    private var layoutWidth: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        this.layoutWidth = parent.width / 3 * 2
        return ItemViewHolder(
            ItemFatrueiChatroomTxtBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), layoutWidth)
    }

    class ItemViewHolder(private val itemBinding: ItemFatrueiChatroomTxtBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ChatroomItemUiState, layoutWidth: Int) {
            if (item.cleanerId != 0) {
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
            return oldItem.msgClnBackId == newItem.msgClnBackId
        }

        override fun areContentsTheSame(
            oldItem: ChatroomItemUiState,
            newItem: ChatroomItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }
}