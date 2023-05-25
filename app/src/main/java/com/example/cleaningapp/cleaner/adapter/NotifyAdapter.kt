package com.example.cleaningapp.cleaner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.NotifyItemUiState
import com.example.cleaningapp.databinding.ItemFatrueiNotifyBinding

class NotifyAdapter : ListAdapter<NotifyItemUiState, NotifyAdapter.ItemViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemFatrueiNotifyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(private val itemBinding: ItemFatrueiNotifyBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: NotifyItemUiState) {
            with(itemBinding) {
                tvNotifyStatus.text = item.notifyStatus
                tvNotifyGo.text =
                    if (item.notifyStatus == "客訴通知") "前往客服聯繫"
                    else "查看已成立訂單"
                tvNotifyDate.text = item.notifyDate
                clNotify.setOnClickListener {
                    if (item.notifyStatus == "客訴通知") Navigation.findNavController(it)
                        .navigate(R.id.action_notifyFragment_to_contactWindowFragment)
                    else Navigation.findNavController(it)
                        .navigate(R.id.action_notifyFragment_to_orderStateFragment)
                }
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<NotifyItemUiState>() {
        override fun areItemsTheSame(
            oldItem: NotifyItemUiState,
            newItem: NotifyItemUiState
        ): Boolean {
            return oldItem.notifyId == newItem.notifyId
        }

        override fun areContentsTheSame(
            oldItem: NotifyItemUiState,
            newItem: NotifyItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }
}