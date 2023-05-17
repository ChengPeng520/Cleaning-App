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
    ListAdapter<ChatroomItemUiState, CleanerChatroomAdapter.MyViewHodler>(ChatroomDiffCallBack()) {
    private var layoutWidth: Int = 0

    class ChatroomDiffCallBack : DiffUtil.ItemCallback<ChatroomItemUiState>() {
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

    class MyViewHodler(private val itemBinding: ItemFatrueiChatroomTxtBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(chatroomItem: ChatroomItemUiState, layoutWidth: Int) {
            if (chatroomItem.fromId == 0) {
                itemBinding.tvChatroomTalkTo.visibility = View.VISIBLE
                itemBinding.tvChatroomTalkTo.text = chatroomItem.text
                itemBinding.tvChatroomTalkTo.maxWidth = layoutWidth
            } else {
                itemBinding.tvChatroomTalkFrom.visibility = View.VISIBLE
                itemBinding.tvChatroomTalkFrom.text = chatroomItem.text
                itemBinding.tvChatroomTalkFrom.maxWidth = layoutWidth
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHodler {
        this.layoutWidth = parent.width / 3 * 2
        return MyViewHodler(
            ItemFatrueiChatroomTxtBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHodler, position: Int) {
        holder.onBind(getItem(position), layoutWidth)
    }
}